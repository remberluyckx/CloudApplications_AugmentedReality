/*
 *  SimpleInteractiveRenderer.java
 *  ARToolKit5
 *
 *  Disclaimer: IMPORTANT:  This Daqri software is supplied to you by Daqri
 *  LLC ("Daqri") in consideration of your agreement to the following
 *  terms, and your use, installation, modification or redistribution of
 *  this Daqri software constitutes acceptance of these terms.  If you do
 *  not agree with these terms, please do not use, install, modify or
 *  redistribute this Daqri software.
 *
 *  In consideration of your agreement to abide by the following terms, and
 *  subject to these terms, Daqri grants you a personal, non-exclusive
 *  license, under Daqri's copyrights in this original Daqri software (the
 *  "Daqri Software"), to use, reproduce, modify and redistribute the Daqri
 *  Software, with or without modifications, in source and/or binary forms;
 *  provided that if you redistribute the Daqri Software in its entirety and
 *  without modifications, you must retain this notice and the following
 *  text and disclaimers in all such redistributions of the Daqri Software.
 *  Neither the name, trademarks, service marks or logos of Daqri LLC may
 *  be used to endorse or promote products derived from the Daqri Software
 *  without specific prior written permission from Daqri.  Except as
 *  expressly stated in this notice, no other rights or licenses, express or
 *  implied, are granted by Daqri herein, including but not limited to any
 *  patent rights that may be infringed by your derivative works or by other
 *  works in which the Daqri Software may be incorporated.
 *
 *  The Daqri Software is provided by Daqri on an "AS IS" basis.  DAQRI
 *  MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 *  THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS
 *  FOR A PARTICULAR PURPOSE, REGARDING THE DAQRI SOFTWARE OR ITS USE AND
 *  OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.
 *
 *  IN NO EVENT SHALL DAQRI BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION,
 *  MODIFICATION AND/OR DISTRIBUTION OF THE DAQRI SOFTWARE, HOWEVER CAUSED
 *  AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE),
 *  STRICT LIABILITY OR OTHERWISE, EVEN IF DAQRI HAS BEEN ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 *
 *  Copyright 2015 Daqri, LLC.
 *  Copyright 2011-2015 ARToolworks, Inc.
 *
 *  Author(s): Julian Looser, Philip Lamb
 *
 */

package org.artoolkit.ar.samples.ARSimpleInteraction;

import android.util.Log;
import android.widget.TextView;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.rendering.ARRenderer;
import org.artoolkit.ar.base.rendering.Cube;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * A simple Renderer that adds a marker and draws a spinning cube on it. The spinning is toggled
 * in the {@link #click} method, which is called from the activity when the user taps the screen.
 */
public class SimpleInteractiveRenderer extends ARRenderer {

    private int markerIDA = -1;
    private int markerIDB = -1;
    private int markerIDC = -1;
    private int markerIDD = -1;
    private int markerIDG = -1;

    public List<String> markersDetected = new ArrayList<String>();
    /*public Boolean a = FALSE;
    public Boolean b = FALSE;
    public Boolean c = FALSE;
    public Boolean d = FALSE;
    public Boolean g = FALSE;*/

    private Cube cube = new Cube(40.0f, 0.0f, 0.0f, 20.0f);
    private float angle = 0.0f;
    private boolean spinning = false;

    /**
     * By overriding {@link #configureARScene}, the markers and other settings can be configured
     * after the native library is initialised, but prior to the rendering actually starting.
     */
    @Override
    public boolean configureARScene() {

        markerIDA = ARToolKit.getInstance().addMarker("single;Data/antwoord_a.patt;80");
        markerIDB = ARToolKit.getInstance().addMarker("single;Data/antwoord_b.patt;80");
        markerIDC = ARToolKit.getInstance().addMarker("single;Data/antwoord_c.patt;80");
        markerIDD = ARToolKit.getInstance().addMarker("single;Data/antwoord_d.patt;80");
        markerIDG = ARToolKit.getInstance().addMarker("single;Data/antwoord_g.patt;80");
        if (markerIDA < 0) return false;

        return true;

    }

    /*public void click() {
        spinning = !spinning;
    }*/

    public void draw(GL10 gl) {
        //Log.d("myTag", "used");
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadMatrixf(ARToolKit.getInstance().getProjectionMatrix(), 0);

        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glFrontFace(GL10.GL_CW);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        if (ARToolKit.getInstance().queryMarkerVisible(markerIDA)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDA), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("A")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("A");
            }
            }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("A")) {
                    markersDetected.remove(i);
                }
            }
        }
            //if (spinning) angle += 5.0f;

        /*else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("A")) {
                    markersDetected.remove(i);
                }
            }
        }*/
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDB)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDB), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("B")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("B");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("B")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDC)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDC), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("C")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("C");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("C")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDD)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDD), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("D")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("D");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("D")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDG)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDG), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("G")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("G");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("G")) {
                    markersDetected.remove(i);
                }
            }
        }


    }

}
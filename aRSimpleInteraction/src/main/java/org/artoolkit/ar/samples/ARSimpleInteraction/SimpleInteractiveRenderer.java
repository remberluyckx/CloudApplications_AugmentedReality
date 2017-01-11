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

    private int markerIDa1 = -1;
    private int markerIDa2 = -1;
    private int markerIDa3 = -1;
    private int markerIDb1 = -1;
    private int markerIDb2 = -1;
    private int markerIDb3 = -1;
    private int markerIDc1 = -1;
    private int markerIDc2 = -1;
    private int markerIDc3 = -1;



    public List<String> markersDetected = new ArrayList<String>();

    private Cube cube = new Cube(40.0f, 0.0f, 0.0f, 20.0f);
    private float angle = 0.0f;
    private boolean spinning = false;

    /**
     * By overriding {@link #configureARScene}, the markers and other settings can be configured
     * after the native library is initialised, but prior to the rendering actually starting.
     */
    @Override
    public boolean configureARScene() {

        markerIDa1 = ARToolKit.getInstance().addMarker("single;Data/a1.patt;80");
        markerIDa2 = ARToolKit.getInstance().addMarker("single;Data/a2.patt;80");
        markerIDa3 = ARToolKit.getInstance().addMarker("single;Data/a3.patt;80");
        markerIDb1 = ARToolKit.getInstance().addMarker("single;Data/b1.patt;80");
        markerIDb2 = ARToolKit.getInstance().addMarker("single;Data/b2.patt;80");
        markerIDb3 = ARToolKit.getInstance().addMarker("single;Data/b3.patt;80");
        markerIDc1 = ARToolKit.getInstance().addMarker("single;Data/c1.patt;80");
        markerIDc2 = ARToolKit.getInstance().addMarker("single;Data/c2.patt;80");
        markerIDc3 = ARToolKit.getInstance().addMarker("single;Data/c3.patt;80");

        //if (markerIDA < 0) return false;

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

        if (ARToolKit.getInstance().queryMarkerVisible(markerIDb1)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDb1), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("b1")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("b1");
            }
            }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("b1")) {
                    markersDetected.remove(i);
                }
            }
        }

        if (ARToolKit.getInstance().queryMarkerVisible(markerIDb2)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDb2), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("b2")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("b2");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("b2")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDb3)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDb3), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("b3")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("b3");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("b3")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDc1)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDc1), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("c1")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("c1");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("c1")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDc2)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDc2), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("c2")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("c2");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("c2")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDc3)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDc3), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("c3")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("c3");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("c3")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDa1)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDa1), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("a1")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("a1");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("a1")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDa2)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDa2), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("a2")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("a2");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("a2")) {
                    markersDetected.remove(i);
                }
            }
        }
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDa3)) {

            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDa3), 0);

            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            Boolean temp = TRUE;
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("a3")) {
                    temp = FALSE;
                }
            }
            if (temp) {
                markersDetected.add("a3");
            }
            //if (spinning) angle += 5.0f;
        }
        else {
            for ( int i = 0;  i < markersDetected.size(); i++) {
                String tempName = markersDetected.get(i);
                if(tempName.equals("a3")) {
                    markersDetected.remove(i);
                }
            }
        }

    }

}
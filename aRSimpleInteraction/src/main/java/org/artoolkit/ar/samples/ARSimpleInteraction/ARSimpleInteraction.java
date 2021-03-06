/*
 *  ARSimpleInteraction.java
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

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.artoolkit.ar.base.ARActivity;
import org.artoolkit.ar.base.rendering.ARRenderer;

/**
 * Another simple example that includes a small amount of user interaction.
 */
public class ARSimpleInteraction extends ARActivity {

    /**
     * A custom renderer is used to produce a new visual experience.
     */
    private SimpleInteractiveRenderer simpleRenderer = new SimpleInteractiveRenderer();

    /**
     * The FrameLayout where the AR view is displayed.
     */
    private FrameLayout mainLayout;
    public TextView markers;
    Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        btn = (Button) findViewById(R.id.button);
        mainLayout = (FrameLayout) this.findViewById(R.id.mainLayout);
        markers = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleRenderer.markersDetected.size() == 0)
                    markers.setText("Markers detected: none"); // alle elementen in lijst hier zetten
                else if (simpleRenderer.markersDetected.size() == 1) {
                    markers.setText("1 Marker detected: " + simpleRenderer.markersDetected.get(0));
                }
                else if (simpleRenderer.markersDetected.size() == 2) {
                    markers.setText("2 Markers detected: " + simpleRenderer.markersDetected.get(0) + ", " + simpleRenderer.markersDetected.get(1));
                }
                else if (simpleRenderer.markersDetected.size() == 3) {
                    markers.setText("3 Markers detected: " + simpleRenderer.markersDetected.get(0) + ", " + simpleRenderer.markersDetected.get(1) + ", " + simpleRenderer.markersDetected.get(2));
                }
                else if (simpleRenderer.markersDetected.size() == 4) {
                    markers.setText("4 Markers detected: " + simpleRenderer.markersDetected.get(0) + ", " + simpleRenderer.markersDetected.get(1) + ", " + simpleRenderer.markersDetected.get(2) + ", " + simpleRenderer.markersDetected.get(3));
                }
                else if (simpleRenderer.markersDetected.size() == 5) {
                    markers.setText("5 Markers detected: " + simpleRenderer.markersDetected.get(0) + ", " + simpleRenderer.markersDetected.get(1) + ", " + simpleRenderer.markersDetected.get(2) + ", " + simpleRenderer.markersDetected.get(3) + ", " + simpleRenderer.markersDetected.get(4));
                }
                else {markers.setText("Too much items in list");}
            }
        });

        // When the screen is tapped, inform the renderer and vibrate the phone
        /*mainLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                simpleRenderer.click();

                Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vib.vibrate(100);
            }

        });*/


    }

    /**
     * By overriding {@link supplyRenderer}, the custom renderer will be used rather than
     * the default renderer which does nothing.
     *
     * @return The custom renderer to use.
     */
    @Override
    protected ARRenderer supplyRenderer() {
        return simpleRenderer;
    }

    /**
     * By overriding {@link supplyFrameLayout}, the layout within this Activity's UI will be
     * used.
     */
    @Override
    protected FrameLayout supplyFrameLayout() {
        return mainLayout;

    }


}
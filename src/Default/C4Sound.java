package Default;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import javax.swing.JApplet;

public class C4Sound extends JApplet {

	public AudioClip titleSound;
	public boolean toggleSound = true;
	
	
	// music
		public void music() {
			// make sound

			try {
				titleSound = Applet
						.newAudioClip(new URL(
								"file:\\E:\\ALEVEL\\Computing\\Project\\Workspace\\Connect4\\src\\Default\\music\\titleSound.wav"));
				titleSound.loop();
			} catch (java.net.MalformedURLException e) {
				System.out.println(e);
			}
		}

		// drop sound
		public void dropSound() {
			// make sound
			AudioClip clip;

			if (toggleSound == false) {

			} else {
				try {
					clip = Applet
							.newAudioClip(new URL(
									"file:\\E:\\ALEVEL\\Computing\\Project\\Workspace\\Connect4\\src\\Default\\music\\dropSound.wav"));
					clip.play();
				} catch (java.net.MalformedURLException e) {
					System.out.println(e);
				}
			}
		}

		// win sound
		public void winSound() {
			// make sound
			AudioClip clip;
			if (toggleSound == false) {

			} else {
				try {
					clip = Applet
							.newAudioClip(new URL(
									"file:\\E:\\ALEVEL\\Computing\\Project\\Workspace\\Connect4\\src\\Default\\music\\winSound.wav"));
					clip.play();
				} catch (java.net.MalformedURLException e) {
					System.out.println(e);
				}
			}
		}
	
	
	
		
	}
	

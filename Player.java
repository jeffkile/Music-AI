/*
 *	SimpleMidiPlayer.java
 *
 *	This file is part of jsresources.org
 */

/*
 * Copyright (c) 1999 - 2001 by Matthias Pfisterer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
|<---            this code is formatted to fit into 80 columns             --->|
*/

import java.lang.Thread;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
import javax.sound.midi.Instrument;


public class Player extends Thread
{
	/*
	  These variables are not really intended to be static in a
	  meaning of (good) design. They are used by inner classes, so they
	  can't just be automatic variables. There were three possibilities:

	  a) make them instance variables and instantiate the object they
	  belong to. This is clean (and is how you should do it in a real
	  application), but would have made the example more complex.

	  b) make them automatic final variables inside main(). Design-wise,
	  this is better than static, but automatic final is something that
	  is still like some black magic to me.

	  c) make them static variables, as it is done here. This is quite bad
	  design, because if you have global variables, you can't easily do
	  the thing they are used for two times in concurrency without risking
	  indeterministic behaviour. However, it makes the example easy to
	  read.
	 */
	private Sequencer	sm_sequencer;
	private Synthesizer	sm_synthesizer;
  private boolean DEBUG;

  public Player(boolean debug) {
    this();
    this.DEBUG = debug;
  }

	public Player(){
    DEBUG = false;
		
		sm_sequencer = null;
		sm_synthesizer = null;

		/*
		 *	Now, we need a Sequencer to play the sequence.
		 *	Here, we simply request the default sequencer.
		 */

		try
		{
			sm_sequencer = MidiSystem.getSequencer();
			
		}
		catch (MidiUnavailableException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		if (sm_sequencer == null)
		{
			out("SimpleMidiPlayer.main(): can't get a Sequencer");
			System.exit(1);
		}

		/*
		 *	The Sequencer is still a dead object.
		 *	We have to open() it to become live.
		 *	This is necessary to allocate some ressources in
		 *	the native part.
		 */
		try
		{
			sm_sequencer.open();
		}
		catch (MidiUnavailableException e)
		{
			e.printStackTrace();
			System.exit(1);
		}	
		
	}
	
	public void run(Sequence playMe)
	{
    if (DEBUG) {
		  System.out.println("In Player");
    }
		
		
		/*
		 *	There is a bug in the Sun jdk1.3/1.4.
		 *	It prevents correct termination of the VM.
		 *	So we have to exit ourselves.
		 *	To accomplish this, we register a Listener to the Sequencer.
		 *	It is called when there are "meta" events. Meta event
		 *	47 is end of track.
		 *
		 *	Thanks to Espen Riskedal for finding this trick.
		 */
/*		sm_sequencer.addMetaEventListener(new MetaEventListener()
			{
				public void meta(MetaMessage event)
				{
					if (event.getType() == 47)
					{
						sm_sequencer.close();
						if (sm_synthesizer != null)
						{
							sm_synthesizer.close();
						}
						System.out.println("exiting");
						System.exit(0);
					}
				}
			});
*/

		/*
		 *	Next step is to tell the Sequencer which
		 *	Sequence it has to play. In this case, we
		 *	set it as the Sequence object created above.
		 */
		try
		{
			sm_sequencer.setSequence(playMe);
		}
		catch (InvalidMidiDataException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		/*
		 *	Now, we set up the destinations the Sequence should be
		 *	played on. Here, we try to use the default
		 *	synthesizer. With some Java Sound implementations
		 *	(Sun jdk1.3/1.4 and others derived from this codebase),
		 *	the default sequencer and the default synthesizer
		 *	are combined in one object. We test for this
		 *	condition, and if it's true, nothing more has to
		 *	be done. With other implementations (namely Tritonus),
		 *	sequencers and synthesizers are always seperate
		 *	objects. In this case, we have to set up a link
		 *	between the two objects manually.
		 *
		 *	By the way, you should never rely on sequencers
		 *	being synthesizers, too; this is a highly non-
		 *	portable programming style. You should be able to
		 *	rely on the other case working. Alas, it is only
		 *	partly true for the Sun jdk1.3/1.4.
		 */
		if (! (sm_sequencer instanceof Synthesizer))
		{
			/*
			 *	We try to get the default synthesizer, open()
			 *	it and chain it to the sequencer with a
			 *	Transmitter-Receiver pair.
			 */
			try
			{
				//Testing to see what midi devices are available
				for(MidiDevice.Info x : MidiSystem.getMidiDeviceInfo()) {
					MidiDevice device = MidiSystem.getMidiDevice(x);

          if (DEBUG) {
            System.out.println(x);
            System.out.println(device.getDeviceInfo().getName());
            System.out.println("Max Transmitters: " + device.getMaxTransmitters());
            System.out.println("Max Reciever " + device.getMaxReceivers());
          }

					if(device.getMaxReceivers() != 0 && device.getDeviceInfo().getName().contains("USB")){
          
          //sm_synthesizer = MidiSystem.getSynthesizer();
          //	Instrument[] availInstruments = sm_synthesizer.getAvailableInstruments();
          //	System.out.println(availInstruments.length);
          //	for(int i=0; i<availInstruments.length; i++)
          //		System.out.println("instruments" + availInstruments[i].toString());
            //sm_synthesizer.open();
            device.open();
            Receiver	usbReceiver = device.getReceiver();
            Transmitter	seqTransmitter = sm_sequencer.getTransmitter();
            seqTransmitter.setReceiver(usbReceiver);
            
            break;
					}
				}
				
				
				// sm_synthesizer = MidiSystem.getSynthesizer();
				// //	Instrument[] availInstruments = sm_synthesizer.getAvailableInstruments();
				// //	System.out.println(availInstruments.length);
				// //	for(int i=0; i<availInstruments.length; i++)
				// //		System.out.println("instruments" + availInstruments[i].toString());
				// 	sm_synthesizer.open();
				// 	Receiver	synthReceiver = sm_synthesizer.getReceiver();
				// 	Transmitter	seqTransmitter = sm_sequencer.getTransmitter();
				// 	seqTransmitter.setReceiver(synthReceiver);
					
				

			}
			catch (MidiUnavailableException e)
			{
				System.out.println("Unable to open synthesizer");
				e.printStackTrace();
			}
		}
    

    //try opening the reciever
    //

		/*
		 *	Now, we can start over.
		 */
		sm_sequencer.start();

		try{
			while(sm_sequencer.isRunning())
				; //wait?
		}
		catch(Exception e){
			System.out.println("Thread excption in Player.java:\n" + e.toString());
		}
    finally {
  		sm_sequencer.close();
    }
	}



	private static void printUsageAndExit()
	{
		out("SimpleMidiPlayer: usage:");
		out("\tjava SimpleMidiPlayer <midifile>");
		System.exit(1);
	}



	private static void out(String strMessage)
	{
		System.out.println(strMessage);
	}
}



/*** SimpleMidiPlayer.java ***/


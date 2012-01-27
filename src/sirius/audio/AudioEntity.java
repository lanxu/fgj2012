package sirius.audio;

import org.lwjgl.util.WaveData;

import sirius.Entity;


public class AudioEntity {
	private Entity entity_;
	private WaveData waveFile_;
	public AudioEntity(Entity entity) {
		entity_ = entity;
	}
	
	public void setAudioSource(String filename) {
		waveFile_ = WaveData.create(filename);
		/*
		AL10.alGenBuffers(buffer);
		AL10.alBufferData(buffer.get(THUNDER), waveFile.format, waveFile.data, waveFile.samplerate);
		*/
	}
}

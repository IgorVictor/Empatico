package com.example.empatico;

public class Component {
	private String imagePath,soundPath,msgToSend;

	public Component(String imagePath, String soundPath, String msgToSend) throws Exception {
		if(imagePath == "" || imagePath == null || msgToSend == "" || msgToSend == null)
			throw new Exception("Componente invalido.");
	
		this.imagePath = imagePath;
		this.soundPath = soundPath;
		this.msgToSend = msgToSend;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getSoundPath() {
		return soundPath;
	}


	public String getMsgToSend() {
		return msgToSend;
	}

	
	
	
	
	
}

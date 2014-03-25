package com.example.empatico.models;

public class Component {
	private String imagePath,soundPath,msgToSend;
	private int id;
	public Component(int id, String imagePath, String soundPath, String msgToSend) throws Exception {
		if(imagePath == "" || imagePath == null || msgToSend == "" || msgToSend == null || id <= 0)
			throw new Exception("Componente invalido.");
	
		this.id = id;
		this.imagePath = imagePath;
		this.soundPath = soundPath;
		this.msgToSend = msgToSend;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String path){
		this.imagePath = path;
	}
	
	public String getSoundPath() {
		return soundPath;
	}


	public String getMsgToSend() {
		return msgToSend;
	}

	
	
	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return id + "|" + imagePath + " | " + soundPath + " | " + msgToSend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result
				+ ((msgToSend == null) ? 0 : msgToSend.hashCode());
		result = prime * result
				+ ((soundPath == null) ? 0 : soundPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Component))
			return false;
		Component comp = (Component) obj;
		
		return this.getId() == comp.getId();
				
	}
	
	
	
	
}

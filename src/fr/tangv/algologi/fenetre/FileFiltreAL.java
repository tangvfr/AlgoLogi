package fr.tangv.algologi.fenetre;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileFiltreAL extends FileFilter {

	@Override
	public boolean accept(File file) {
		if (file.isFile() && !file.getAbsolutePath().endsWith(".algologi")) return false;
		return true;
	}

	@Override
	public String getDescription() {
		return "*.algologi";
	}

}

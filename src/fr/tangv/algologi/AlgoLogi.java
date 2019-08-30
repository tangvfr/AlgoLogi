package fr.tangv.algologi;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import fr.tangv.algologi.fenetre.Fenetre;

public class AlgoLogi {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new HiFiLookAndFeel());
			Color backgroundColor = new Color(65, 65, 65);
			UIManager.put("Panel.background", backgroundColor);
			UIManager.put("RadioButtonMenuItem.background", backgroundColor);
			UIManager.put("ToolTip.background", backgroundColor);
			UIManager.put("FormattedTextField.background", backgroundColor);
			UIManager.put("ToolTip.backgroundInactive", backgroundColor);
			UIManager.put("OptionPane.errorDialog.border.background", backgroundColor);
			UIManager.put("OptionPane.warningDialog.border.background", backgroundColor);
			UIManager.put("Tree.background", backgroundColor);
			UIManager.put("TextField.background", backgroundColor);
			UIManager.put("List.background", backgroundColor);
			UIManager.put("EditorPane.background", backgroundColor);
			UIManager.put("ProgressBar.background", backgroundColor);
			UIManager.put("Separator.background", backgroundColor);
			UIManager.put("TableHeader.background", backgroundColor);
			UIManager.put("OptionPane.warningDialog.titlePane.background", backgroundColor);
			UIManager.put("OptionPane.questionDialog.border.background", backgroundColor);
			UIManager.put("OptionPane.background", backgroundColor);
			UIManager.put("Label.background", backgroundColor);
			UIManager.put("ComboBox.background", backgroundColor);
			UIManager.put("ToolBar.background", backgroundColor);
			UIManager.put("PopupMenu.background", backgroundColor);
			UIManager.put("DesktopIcon.background", backgroundColor);
			UIManager.put("TextPane.background", backgroundColor);
			UIManager.put("Spinner.background", backgroundColor);
			UIManager.put("Desktop.background", backgroundColor);
			UIManager.put("ScrollPane.background", backgroundColor);
			UIManager.put("SplitPane.background", backgroundColor);
			UIManager.put("ColorChooser.background", backgroundColor);
			UIManager.put("TextArea.background", backgroundColor);
			UIManager.put("Button.background", backgroundColor);
			UIManager.put("OptionPane.errorDialog.titlePane.background", backgroundColor);
			UIManager.put("Viewport.background", backgroundColor);
			UIManager.put("ToggleButton.background", backgroundColor);
			UIManager.put("OptionPane.questionDialog.titlePane.background", backgroundColor);
			UIManager.put("PasswordField.background", backgroundColor);
			UIManager.put("Slider.background", backgroundColor);
			UIManager.put("ScrollBar.background", backgroundColor);
			UIManager.put("RadioButton.background", backgroundColor);
			UIManager.put("Table.background", backgroundColor);
			UIManager.put("CheckBox.background", backgroundColor);
			UIManager.put("CheckBoxMenuItem.background", backgroundColor);
			UIManager.put("MenuBar.background", backgroundColor);
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
	}

}

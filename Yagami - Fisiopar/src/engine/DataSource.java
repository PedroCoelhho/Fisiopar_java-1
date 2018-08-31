package engine;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import view.telas.*;

/**
 *
 * @author Juan
 */
public class DataSource {
    // Acesso Global
    public static final DataSource DS = new DataSource();
    
    // Telas
    public JInternalFrame[] telas = {
        new Base(),      // 0
        new ViewPaciente()  // 1
    };
    
    // Icones
    public Icon[] icones = {
        new ImageIcon(".\\img\\iconAgenda.png"),
        new ImageIcon(".\\img\\iconPacientes.png")
    };
    
    // Atalhos
    public DComponent[] atalhos = {
        new DComponent(10, 18, telas[0], icones[0]),
        new DComponent(10, 98, telas[1], icones[1])
    };
    
}

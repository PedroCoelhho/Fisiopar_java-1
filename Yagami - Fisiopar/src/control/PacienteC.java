/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PacienteDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Paciente;

/**
 *
 * @author Juan
 */
public class PacienteC {
    public static final PacienteC CONTROL = new PacienteC();
    
    PacienteDao pacDao = new PacienteDao();
    
    public boolean create(Paciente paciente) {
        if(pacDao.salvar(paciente)) {
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");
            return true;
        } else {
            return false;
        }
    }
    
    public void update(Paciente paciente) {
        if(pacDao.alterar(paciente)) {
            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");
            
        }
    }
    
    public void delete(int id) {
        if(pacDao.deletar(id)) {
            JOptionPane.showMessageDialog(null, "Paciente deletado com sucesso!");
        }
    }
    
    public void listTable(JTable tabela) {
        // Atualizar Tabela
        List<Paciente> listPaciente = pacDao.listar();
        
        // Clear
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        
        for(Paciente h : listPaciente) {
            //CI_Roupa rpa = new CI_Roupa(r.getNome(), r);
            model = (DefaultTableModel) tabela.getModel();
            model.addRow(new Object[]{h.getId(), h.getNome(), h.getData_nasc(), h.getSexo()});
        }
    }
}

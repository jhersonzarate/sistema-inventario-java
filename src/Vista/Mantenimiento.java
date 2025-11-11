package Vista;

import DAO.AlumnoDAO;
import DAO.CursoDAO;
import Modelo.Alumno;
import Modelo.Curso;
import Util.Conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Mantenimiento extends javax.swing.JPanel {

    AlumnoDAO alumnoDAO = new AlumnoDAO();
    Alumno alumno = new Alumno();

    CursoDAO cursoDAO = new CursoDAO();
    Curso curso = new Curso();

    public Mantenimiento() {
        initComponents();
        cargarAlumnos();
        cargarCursos();
    }

    public void limpiarAlumno() {
        txtCodigoAlumno.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDNI.setText("");
        txtCelular.setText("");
        txtEdad.setText("");
    }

    public void limpiarCurso() {
        txtCodigoCurso.setText("");
        txtAsignatura.setText("");
        txtCiclo.setText("");
        txtCreditos.setText("");
        txtHoras.setText("");
    }

    public void cargarAlumnos() {
        DefaultTableModel tablaAlumnos = new DefaultTableModel(null, new String[]{"Codigo", "Nombres", "Apellidos", "DNI", "Edad", "Celular", "Estado"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String sql = "SELECT * FROM Alumno";

        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int codigo = rs.getInt("codAlumno");
                String nombres = rs.getString("Nombres");
                String apellidos = rs.getString("Apellidos");
                String dni = rs.getString("DNI");
                int edad = rs.getInt("Edad");
                String celular = rs.getString("Celular");
                int estado = rs.getInt("Estado");
                tablaAlumnos.addRow(new Object[]{codigo, nombres, apellidos, dni, edad, celular, estado});
            }

            tblAlumnos.setModel(tablaAlumnos);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tabla de Cursos: " + e.getMessage());
        }
    }

    public void cargarCursos() {
        DefaultTableModel tablaCursos = new DefaultTableModel(null, new String[]{"Codigo", "Asignatura", "Ciclo", "Créditos", "Horas"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String sql = "SELECT * FROM Curso ORDER BY codCurso";

        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int codigo = rs.getInt("codCurso");
                String asignatura = rs.getString("Asignatura");
                int ciclo = rs.getInt("Ciclo");
                int creditos = rs.getInt("Creditos");
                int horas = rs.getInt("Horas");
                tablaCursos.addRow(new Object[]{codigo, asignatura, ciclo, creditos, horas});
            }

            tblCursos.setModel(tablaCursos);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tabla de Alumnos: " + e.getMessage());
        }
    }

    private boolean validarCurso() {

        // 1️⃣ Verificar que todos los campos estén llenos
        if (txtCodigoCurso.getText().trim().isEmpty()
                || txtAsignatura.getText().trim().isEmpty()
                || txtCiclo.getText().trim().isEmpty()
                || txtCreditos.getText().trim().isEmpty()
                || txtHoras.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos antes de continuar.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar código de 4 dígitos
        String codigoStr = txtCodigoCurso.getText().trim();
        if (!codigoStr.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(null, "El código del curso debe tener exactamente 4 dígitos numéricos.", "Código inválido", JOptionPane.WARNING_MESSAGE);
            txtCodigoCurso.requestFocus();
            return false;
        }

        // Validar ciclo entre 1 y 10
        try {
            int ciclo = Integer.parseInt(txtCiclo.getText().trim());
            if (ciclo < 1 || ciclo > 10) {
                JOptionPane.showMessageDialog(null, "El ciclo debe estar entre 1 y 10.", "Ciclo inválido", JOptionPane.WARNING_MESSAGE);
                txtCiclo.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ciclo debe ser un número válido.", "Error de formato", JOptionPane.WARNING_MESSAGE);
            txtCiclo.requestFocus();
            return false;
        }

        // (Opcional) Validar créditos y horas positivos
        try {
            int creditos = Integer.parseInt(txtCreditos.getText().trim());
            int horas = Integer.parseInt(txtHoras.getText().trim());
            if (creditos <= 0 || horas <= 0) {
                JOptionPane.showMessageDialog(null, "Créditos y horas deben ser mayores que cero.", "Valores inválidos", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Créditos y horas deben ser valores numéricos válidos.", "Error de formato", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true; // ✅ Todo está correcto
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMantenimiento = new javax.swing.JTabbedPane();
        pnlAlumnos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCodigoAlumno = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlCursos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtHoras = new javax.swing.JTextField();
        txtCodigoCurso = new javax.swing.JTextField();
        txtAsignatura = new javax.swing.JTextField();
        txtCiclo = new javax.swing.JTextField();
        txtCreditos = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        btnRegistrarCur = new javax.swing.JButton();
        btnModificarCur = new javax.swing.JButton();
        btnEliminarCur = new javax.swing.JButton();
        btnLimpiarCur = new javax.swing.JButton();

        panelMantenimiento.setMinimumSize(new java.awt.Dimension(720, 640));

        pnlAlumnos.setBackground(new java.awt.Color(255, 255, 255));
        pnlAlumnos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAlumnos);

        pnlAlumnos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 618, 260));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Mantenimiento de Alumnos");
        pnlAlumnos.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 25, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Código:");
        pnlAlumnos.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombres:");
        pnlAlumnos.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Apellidos:");
        pnlAlumnos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("DNI:");
        pnlAlumnos.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Celular:");
        pnlAlumnos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Edad:");
        pnlAlumnos.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, -1, -1));

        txtCodigoAlumno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlAlumnos.add(txtCodigoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 145, -1));

        txtNombres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlAlumnos.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 145, -1));

        txtApellidos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlAlumnos.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 145, -1));

        txtDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlAlumnos.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 189, -1));

        txtCelular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlAlumnos.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 189, -1));

        txtEdad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlAlumnos.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 189, -1));

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        pnlAlumnos.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, -1, -1));

        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setToolTipText("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlAlumnos.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlAlumnos.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, -1));

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setToolTipText("");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlAlumnos.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, -1, -1));

        panelMantenimiento.addTab("Alumnos", pnlAlumnos);

        pnlCursos.setBackground(new java.awt.Color(255, 255, 255));
        pnlCursos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mantenimiento de Cursos");
        pnlCursos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 26, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Código:");
        pnlCursos.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Asignatura:");
        pnlCursos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ciclo:");
        jLabel11.setToolTipText("");
        pnlCursos.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Créditos:");
        jLabel12.setToolTipText("");
        pnlCursos.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Horas:");
        jLabel13.setToolTipText("");
        pnlCursos.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, -1, -1));

        txtHoras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCursos.add(txtHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 180, -1));

        txtCodigoCurso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCursos.add(txtCodigoCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 180, -1));

        txtAsignatura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCursos.add(txtAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 180, -1));

        txtCiclo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCursos.add(txtCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 180, -1));

        txtCreditos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pnlCursos.add(txtCreditos, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 180, -1));

        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCursosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCursos);

        pnlCursos.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 640, 250));

        btnRegistrarCur.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnRegistrarCur.setText("Registrar");
        btnRegistrarCur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCurActionPerformed(evt);
            }
        });
        pnlCursos.add(btnRegistrarCur, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, -1, -1));

        btnModificarCur.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnModificarCur.setText("Modificar");
        btnModificarCur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCurActionPerformed(evt);
            }
        });
        pnlCursos.add(btnModificarCur, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));

        btnEliminarCur.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnEliminarCur.setText("Eliminar");
        btnEliminarCur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCurActionPerformed(evt);
            }
        });
        pnlCursos.add(btnEliminarCur, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, -1, -1));

        btnLimpiarCur.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnLimpiarCur.setText("Limpiar");
        btnLimpiarCur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCurActionPerformed(evt);
            }
        });
        pnlCursos.add(btnLimpiarCur, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, -1, -1));

        panelMantenimiento.addTab("Cursos", pnlCursos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMantenimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        alumno.setNombres(txtNombres.getText());
        alumno.setApellidos(txtApellidos.getText());
        alumno.setDNI(txtDNI.getText());
        alumno.setEdad(Integer.parseInt(txtEdad.getText()));
        alumno.setCelular(txtCelular.getText());

        alumnoDAO.insertarAlumno(alumno);

        cargarAlumnos();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarAlumno();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        alumno.setNombres(txtNombres.getText());
        alumno.setApellidos(txtApellidos.getText());
        alumno.setEdad(Integer.parseInt(txtEdad.getText()));
        alumno.setCelular(txtCelular.getText());
        alumno.setCodAlumno(Integer.parseInt(txtCodigoAlumno.getText()));

        alumnoDAO.actualizarAlumno(alumno);

        cargarAlumnos();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tblAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlumnosMouseClicked
        if (tblAlumnos.getSelectedRow() != -1) {
            int fila = tblAlumnos.getSelectedRow();
            txtCodigoAlumno.setText(tblAlumnos.getValueAt(fila, 0).toString());
            txtNombres.setText(tblAlumnos.getValueAt(fila, 1).toString());
            txtApellidos.setText(tblAlumnos.getValueAt(fila, 2).toString());
            txtDNI.setText(tblAlumnos.getValueAt(fila, 3).toString());
            txtEdad.setText(tblAlumnos.getValueAt(fila, 4).toString());
            txtCelular.setText(tblAlumnos.getValueAt(fila, 5).toString());
        }
    }//GEN-LAST:event_tblAlumnosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        alumno.setCodAlumno(Integer.parseInt(txtCodigoAlumno.getText()));
        alumnoDAO.eliminarAlumno(alumno);

        cargarAlumnos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCurActionPerformed
        if (!validarCurso()) {
            return;
        }

        curso.setCodCurso(Integer.parseInt(txtCodigoCurso.getText()));
        curso.setAsignatura(txtAsignatura.getText());
        curso.setCiclo(Integer.parseInt(txtCiclo.getText()));
        curso.setCreditos(Integer.parseInt(txtCreditos.getText()));
        curso.setHoras(Integer.parseInt(txtHoras.getText()));

        cursoDAO.insertarCurso(curso);

        cargarCursos();
    }//GEN-LAST:event_btnRegistrarCurActionPerformed

    private void btnModificarCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCurActionPerformed

    }//GEN-LAST:event_btnModificarCurActionPerformed

    private void btnEliminarCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarCurActionPerformed

    private void btnLimpiarCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCurActionPerformed
        limpiarCurso();
    }//GEN-LAST:event_btnLimpiarCurActionPerformed

    private void tblCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCursosMouseClicked
        if (tblCursos.getSelectedRow() != -1) {
            int fila = tblCursos.getSelectedRow();
            txtCodigoCurso.setText(tblCursos.getValueAt(fila, 0).toString());
            txtAsignatura.setText(tblCursos.getValueAt(fila, 1).toString());
            txtCiclo.setText(tblCursos.getValueAt(fila, 2).toString());
            txtCreditos.setText(tblCursos.getValueAt(fila, 3).toString());
            txtHoras.setText(tblCursos.getValueAt(fila, 4).toString());
        }
    }//GEN-LAST:event_tblCursosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarCur;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLimpiarCur;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarCur;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrarCur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane panelMantenimiento;
    private javax.swing.JPanel pnlAlumnos;
    private javax.swing.JPanel pnlCursos;
    private static javax.swing.JTable tblAlumnos;
    private javax.swing.JTable tblCursos;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtAsignatura;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCiclo;
    private javax.swing.JTextField txtCodigoAlumno;
    private javax.swing.JTextField txtCodigoCurso;
    private javax.swing.JTextField txtCreditos;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtHoras;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}

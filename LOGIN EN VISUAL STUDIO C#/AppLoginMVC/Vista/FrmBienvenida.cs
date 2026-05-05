// FrmBienvenida.cs 
using AppLoginMVC.Modelo;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace AppLoginMVC
{
    public partial class FrmBienvenida : Form
    {
        public FrmBienvenida(Usuario usuario)
        {
            InitComponentsManual(usuario);
            ConfigurarVentana();
        }

        private void InitComponentsManual(Usuario u)
        {
            BackColor = Color.FromArgb(235, 245, 255);

            // ── Foto del usuario ─────────────────────────── 
            var picFoto = new PictureBox
            {
                Location = new Point(20, 20),
                Size = new Size(130, 130),
                SizeMode = PictureBoxSizeMode.Zoom,
                BorderStyle = BorderStyle.FixedSingle,
                BackColor = Color.FromArgb(210, 230, 250)
            };

            // Cargar foto desde BLOB (byte[]) 
            if (u.Foto != null && u.Foto.Length > 0)
            {
                using (var ms = new System.IO.MemoryStream(u.Foto))
                {
                    picFoto.Image = Image.FromStream(ms);
                }
            }

            // ── Panel de información ─────────────────────── 
            var lblBienvenida = new Label
            {
                Text = "¡Bienvenido al Sistema!",
                Location = new Point(170, 25),
                Size = new Size(280, 30),
                Font = new Font("Arial", 14, FontStyle.Bold),
                ForeColor = Color.FromArgb(26, 60, 107)
            };

            var lblNombre = new Label
            {
                Text = u.NombreCompleto,
                Location = new Point(170, 62),
                Size = new Size(280, 26),
                Font = new Font("Arial", 13),
                ForeColor = Color.FromArgb(50, 50, 50)
            };

            var lblUsuario = new Label
            {
                Text = string.Format("@{0}   |   {1}", u.NombreUsuario, u.Email),
                Location = new Point(170, 92),
                Size = new Size(280, 20),
                Font = new Font("Arial", 9, FontStyle.Italic),
                ForeColor = Color.Gray
            };

            // Nivel de seguridad de la contraseña 
            string etiq = PasswordUtils.GetEtiqueta(u.NivelSeguridad);
            Color col = PasswordUtils.GetColor(u.NivelSeguridad);
            var lblSeg = new Label
            {
                Text = "Seguridad de contraseña: " + etiq,
                Location = new Point(170, 118),
                Size = new Size(280, 20),
                Font = new Font("Arial", 9, FontStyle.Bold),
                ForeColor = col
            };

            // Separador visual 
            var separador = new Panel
            {
                Location = new Point(20, 165),
                Size = new Size(450, 1),
                BackColor = Color.FromArgb(46, 93, 168)
            };

            // Mensaje de último acceso 
            var lblAcceso = new Label
            {
                Text = u.UltimoAcceso.HasValue
                            ? "Último acceso: " + u.UltimoAcceso.Value.ToString("dd/MM/yyyy HH:mm")
                            : "Primer acceso al sistema",
                Location = new Point(20, 175),
                Size = new Size(440, 20),
                Font = new Font("Arial", 9, FontStyle.Italic),
                ForeColor = Color.Gray,
                TextAlign = ContentAlignment.MiddleCenter
            };

            // Botón cerrar sesión 
            var btnSalir = new Button
            {
                Text = "Cerrar Sesión",
                Location = new Point(160, 205),
                Size = new Size(160, 38),
                BackColor = Color.FromArgb(176, 0, 0),
                ForeColor = Color.White,
                Font = new Font("Arial", 11, FontStyle.Bold),
                FlatStyle = FlatStyle.Flat
            };

            btnSalir.Click += (s, e) =>
            {
                new FrmLogin().Show();
                this.Close();
            };

            Controls.AddRange(new Control[]
            {
                picFoto, lblBienvenida, lblNombre, lblUsuario,
                lblSeg, separador, lblAcceso, btnSalir
            });
        }

        private void ConfigurarVentana()
        {
            this.Text = "Bienvenida";
            this.Size = new Size(500, 300);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
        }
    }
}
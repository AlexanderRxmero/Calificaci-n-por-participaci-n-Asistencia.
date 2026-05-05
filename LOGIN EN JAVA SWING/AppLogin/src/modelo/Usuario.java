package modelo;

/**
 * @author Alexander Romero Ramírez
 */
public class Usuario
{
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String usuario;
    private String contrasena;
    private byte[] foto; // BLOB almacenado como arreglo de bytes
    
    // Constructor vacío
    public Usuario()
    {
        
    }
    
    // Constructor completo (sin id, para inserción)
    public Usuario(String nombre, String apellido, String email,
            String usuario, String contrasena, byte[] foto)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.foto = foto;
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int Id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    
    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }
    
    @Override
    public String toString()
    {
        return nombre + " " + apellido + " (" + usuario + ")";
    }
}
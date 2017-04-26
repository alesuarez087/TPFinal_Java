package utils;

import java.util.regex.*;
import java.util.*;
import controlador.Controlador;
import entidades.*;

public class Validate {

	private static Controlador ctrl = new Controlador();
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    public static boolean Email(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    
    public static boolean NombreUsuario(String nom){
    	boolean valid = true; int i = 0;
    	ArrayList<Usuario> list = ctrl.getAllUsuario();
    	do{
    		if(nom.equals(list.get(i).getNombreUsuario())) valid = false;
    		else i++;
    	} while(valid == true && i<list.size());
    	return valid;
    }
    
    public static boolean NombreArtista(String artista){
    	Artista art = ctrl.getOneArtista(artista);
		if(art != null) return false;
			else return true;
    }
    
    public static boolean Descripcion(String desc){ 
		Genero gen = ctrl.getOneGenero(desc);
		if(gen != null) return false;
			else return true;
	}
    
    public static boolean Artista(String desc){ 
		Artista art = ctrl.getOneArtista(desc);
		if(art != null) return false;
			else return true;
	}
    
    public static boolean ArtistaItem(String titulo, String artista){
		boolean valid = true; int i = 0;
		ArrayList<Item> list = ctrl.getAllItemForArtista(artista);
		do{
			if(list.get(i).getTitulo().equals(titulo)) valid = false;
			else i++;
		} while(valid == true && i<list.size());
		return valid;
	}
    
    public static boolean HayStock(int idItem, int cantidad){
    	boolean valid = true;
    	if(ctrl.getOneItem(idItem).getStock()<cantidad) valid = false;
    	return valid;
    }
}

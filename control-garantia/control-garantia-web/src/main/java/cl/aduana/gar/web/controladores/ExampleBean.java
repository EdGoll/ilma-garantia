package cl.aduana.gar.web.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * C&oacute;digo de ejemplo para demostrar uso de primefaces.
 * @author egodoy
 *
 */
@Model
public class ExampleBean {
    private String word = "adfhsd";

    private String anagram;

    /**
     * Genra un "Anagrama" (Secuencia de letras desordenadas)
     * almacena el resultado en la variable anagram.
     */
    public void generateAnagram() {
        List<Character> characters = new ArrayList<Character>();
        for (char c : word.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(word.length());
        while (!characters.isEmpty()) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        this.anagram = output.toString();
    }

    public String getAnagram() {
        return anagram;
    }

    public void setAnagram(String anagram) {
        this.anagram = anagram;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Metodo invocado desde un boton, muestra un mensaje.
     */
    public void buttonAction() {
        addMessage("Welcome to Primefaces!!");
    }

    /**
     * Agrega un mensaje al contexto JSF.
     * @param summary
     */
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
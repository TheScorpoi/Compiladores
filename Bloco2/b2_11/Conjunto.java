import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Conjunto {
    private String[] elementos;

    public Conjunto(String[] elementos) {
        this.elementos = elementos;
    }

    public String[] getElementos() {
        return elementos;
    }

    public Conjunto interset(Conjunto elementsExecute) {

        Set<String> newConj = new HashSet<String>();

        for (int i = 0; i < this.elementos.length; i++) {
            for (int j = 0; j < elementsExecute.elementos.length; j++) {
                if (this.elementos[i].equals(elementsExecute.elementos[j])) {
                    newConj.add(this.elementos[i]); 
                }
            }

        }
        
        String[] newElem = newConj.toArray(new String[newConj.size()]);

        return new Conjunto(newElem);
    }

    public Conjunto union(Conjunto elementsExecute){
        Set<String> newConj = new HashSet<String>();

        for (int i = 0; i < this.elementos.length; i++) {
            newConj.add(this.elementos[i]);
        }

        for (int i = 0; i < elementsExecute.elementos.length; i++) {
            newConj.add(elementsExecute.elementos[i]);
        }

        String[] newElem = newConj.toArray(new String[newConj.size()]);

        return new Conjunto(newElem);
    }

    //tamanho de um conjunto(numeros, virgulas e 2 chavetas) //precisava antes
    //public int tamanhoConj(){
        //num elementos + numvirgulas + 2 chavetas
    //    return this.elementos.length + this.elementos.length - 1 + 2;
    //}

    public Conjunto subtract(Conjunto elementsExecute){
        Set<String> newConj = new HashSet<String>();
        for (int i = 0; i < this.elementos.length; i++) {
           newConj.add(this.elementos[i]); 
        }
        
        for (int i = 0; i < elementsExecute.elementos.length; i++) {
            for (int j = 0; j < this.elementos.length; j++) {
                if(elementsExecute.elementos[i].equals(this.elementos[j])){
                    newConj.remove(elementsExecute.elementos[i]);
                }
            }
        }

        String[] newElem = newConj.toArray(new String[newConj.size()]);
        
        return new Conjunto(newElem);
    }

    @Override
    public String toString() {
        String newStr = "{" + Arrays.toString(elementos).substring(1, Arrays.toString(elementos).length() - 1) + "}";
        return newStr;
    }

    

}

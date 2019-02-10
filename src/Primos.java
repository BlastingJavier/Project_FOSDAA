import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Esta clase mantiene una caché de números primos, para
 * evitar cálculos repetidos, y calcula si un número es primo
 * La cache conserva los primos calculados
 * @author Francisco Javier Nanclares
 * @author Angel Vega
 */
public class Primos {
    // usamos un conjunto ordenado, que implementa TreeSet
    private SortedSet<Integer> primos= new TreeSet<>();
    private int max=1;

    /**
     *
     * @return cache con los primos calculados
     */
    public SortedSet<Integer> getPrimos(){
        return primos;
    }
    
    /**
    *
    * @return String con los primos hasta el atributo maximo
    */
    public String toString(){
        return "Primos hasta "+ max+ " = "+primos;
    }

    /**
     *
     * @param n un número entero
     * @return si n es primo
     */
    public boolean esPrimo(int n){
        if (n<2) return false;
        if (n>max) actualizaPrimos(n);
        return primos.contains(n);
    }
    
    /**
    *
    * @param n un número entero
    * @return SortedSet<Integer> , set de divisores primos del numero n
    */
    public SortedSet<Integer> divisoresPrimos(int n){
    	for (int i=1 ; i<=n ; i++) {
    		if (n % i == 0 ) {
    			if (this.compruebaPrimo(i)) {
    				this.primos.add(i);
    				this.max = i;
    			}
    		}
    	}
    	
    	return this.getPrimos();
    }

    /**
     * Este método llama a compruebaPrimo, y lo añade, si es primo,
     * para todos los números entre max+1 y n. Actualiza max al terminar.
     * @param n
     */
    private void actualizaPrimos(int n){
    	for (int i = max+1 ; i<=n ; i++) {
    		if (compruebaPrimo(i) == true) {
    			this.primos.add(i);
    		}
    	}
    	this.max = n;
    }

    /**
     * Comprueba si n es primo, asumiendo que el conjunto primos
     * está actualizado con todos los anteriores
     * @param n valor a comprobar
     * @return si n es primo
     */
    private boolean compruebaPrimo(int n){
    	boolean esPrimo = true;
    	if (n < 2) {
    		esPrimo = false;
    	}
    	for (int i=2 ; i*i<n ; i++) {
    		if (n%i == 0) {
    			esPrimo = false;
    			break;
    		}
    	}
    	return esPrimo;
    }
    
    
    public static void main(String args[]) {
    	if (args.length<1) {
    		System.out.println("Se esperan al menos 1 parametro");
    	}
    	else {
    		//Como tenemos un constructor por default no hace falta implementarlo
    		Primos primos = new Primos();
    		Primos div_primos = new Primos();
    		int numero;
    		for (String s: args) {
    			numero = Integer.parseInt(s);
    			if (primos.esPrimo(numero)) {
    				System.out.println("El numero " + numero + " es primo: Actualizando primos...");
    			}
    			else {
    				System.out.println("El numero " + numero + " no es primo");
    			}
    			div_primos.divisoresPrimos(numero);
    		}
    		System.out.println("Los primos quedarian:");
    		System.out.println(primos);
    		System.out.println("Los divisores primos son :" + div_primos);
    	}
    }
}

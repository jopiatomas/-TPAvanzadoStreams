import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Scanner leer = new Scanner(System.in);
    public static void main(String[] args) {
        List<Producto> lista = cargarProductos();
        List<Producto> listaAuxiliar = new ArrayList<>();

        int ejercicio;

        do{
            System.out.print("Indique el ejercicio a analizar: ");
            ejercicio = leer.nextInt();
            leer.nextLine();
            switch (ejercicio){
                case 1:
                    listaAuxiliar = lista.stream().filter(p -> p.getCategoria().equals("Electrónica") && p.getPrecio() > 1000)
                            .sorted(Comparator.comparingDouble(Producto::getPrecio).reversed())
                                  .collect(Collectors.toList());

                    System.out.println(listaAuxiliar);

                    break;
                case 2:
                    OptionalDouble precioPromedio = lista
                            .stream()
                            .filter(p -> p.getCategoria().equals("Hogar") && p.getStock() > 0)
                            .mapToDouble(p -> p.getPrecio())
                            .average();

                    System.out.println("precioPromedio = " + precioPromedio.orElse(0));



                    break;
                case 3:


                   Map<String, Producto> masCaroPorCategoria = lista
                           .stream()
                        .collect(Collectors.groupingBy(
                                Producto::getCategoria,
                                Collectors.maxBy((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio()))
                        ))
                        .entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().orElse(null) // En caso de que no haya productos, usamos null
                        ));

                    System.out.println("masCaroPorCategoria = " + masCaroPorCategoria);



                    break;
                case 4:
                        Optional<String> productoDeportes = lista.stream()
                                .filter(p -> p.getCategoria().equals("Deportes") && p.getStock() > 10)
                                .map(p -> p.getNombre().toLowerCase()).findFirst();

                    productoDeportes.orElse("Producto Inexistente");
                    System.out.println(productoDeportes);

                    break;
                case 5:
                    Optional<String> productoMasBarato = lista
                            .stream()
                            .min(Comparator.comparingDouble(p -> p.getStock() * p.getPrecio()))
                            .map(p -> p.getNombre());

                    productoMasBarato.ifPresent(System.out::println);

                    break;
                case 6:
                    List<String> productosConStock = lista
                            .stream()
                            .filter(p -> p.getStock() > 0 && p.getNombre().length() > 5)
                            .map(Producto::getNombre)
                            .sorted()
                            .toList();

                    System.out.println("productosConStock = " + productosConStock);
                        
                       
                    break;
                case 7:
                    OptionalDouble valorPromedio = lista
                            .stream()
                            .mapToDouble(Producto::getPrecio)
                            .average();

                    System.out.println(valorPromedio);

                        Integer cantidadTotalStock = lista
                                .stream()
                                .filter(p -> p.getPrecio() >= valorPromedio.orElse(0))
                                .mapToInt(Producto::getStock)
                                .sum();

                    System.out.println("cantidadTotalStock = " + cantidadTotalStock);


                    break;
                case 8:
                    /// revisar
                    Map<String, Integer> mapCategoriaStock = lista
                            .stream()
                            .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.summingInt(Producto::getStock)))
                            .entrySet()
                            .stream()
                            .filter(p -> p.getValue() >= 3)
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                    System.out.println(mapCategoriaStock);
                    break;
                case 9:
                    List<Producto> listaDescuentos = lista
                            .stream()
                            .filter(p -> p.getStock() > 20)
                            .map(p ->  {
                                p.setPrecio(p.getPrecio() * 0.85);
                                return p;
                })
                            .toList();


                    break;
                case 10:
                    Double ganancia = lista
                            .stream()
                            .mapToDouble(p -> {
                               Double gananciaPorCategoria = 0.0;
                               if (p.getNombre().equals("Electronica")){
                                   gananciaPorCategoria = p.getStock() * (p.getPrecio() * 0.35);
                               }
                               else{
                                   gananciaPorCategoria = p.getStock() * (p.getPrecio() * 0.55);
                               }
                                return gananciaPorCategoria;
                            })
                            .sum();

                    System.out.println("ganancia = " + ganancia);
                    break;
                default:
                    System.out.println("NO EXISTE PAPA");
                    break;
            }
            System.out.print("Indique el ejercicio a analizar: ");
            ejercicio = leer.nextInt();
            leer.nextLine();
        } while (ejercicio != 0);

    }
    public static List<Producto> cargarProductos() {
        return List.of(
                new Producto("Laptop", 1500, "Electrónica", 5),
                new Producto("Smartphone", 800, "Electrónica", 10),
                new Producto("Televisor", 1200, "Electrónica", 3),
                new Producto("Heladera", 2000, "Hogar", 2),
                new Producto("Microondas", 500, "Hogar", 8),
                new Producto("Silla", 150, "Muebles", 12),
                new Producto("Mesa", 300, "Muebles", 7),
                new Producto("Zapatillas", 100, "Deportes", 15),
                new Producto("Pelota", 50, "Deportes", 20),
                new Producto("Bicicleta", 500, "Deportes", 5),
                new Producto("Libro", 30, "Librería", 50),
                new Producto("Cuaderno", 10, "Librería", 100),
                new Producto("Lámpara", 80, "Hogar", 30),
                new Producto("Cafetera", 250, "Hogar", 6),
                new Producto("Auriculares", 120, "Electrónica", 14),
                new Producto("Teclado", 90, "Electrónica", 9),
                new Producto("Mouse", 60, "Electrónica", 18),
                new Producto("Monitor", 700, "Electrónica", 4),
                new Producto("Cama", 800, "Muebles", 2),
                new Producto("Sofá", 1000, "Muebles", 3),
                new Producto("Espejo", 120, "Hogar", 12),
                new Producto("Ventilador", 150, "Hogar", 7),
                new Producto("Patines", 180, "Deportes", 5),
                new Producto("Raqueta", 220, "Deportes", 6),
                new Producto("Taza", 15, "Hogar", 40)
        );

    }


}



package MongoDB;

/**
 * ========================================================
 * ESQUEMA COMPLETO MONGODB JAVA - TEMARIO Tema 5 BD NoSQL
 * Chuleta de referencia rápida. Copia y pega en Eclipse
 * ========================================================
 * 
 * AUTOR: Perplexity AI basado en AP_Tema5_BD_NoSQL_V3.pdf[file:1]
 * FECHA: Marzo 2026
 * 
 * INDICE:
 * 1. Conexión básica
 * 2. MongoCollection métodos
 * 3. Document (construir/acceder)
 * 4. Filtros Filters
 * 5. Ordenación/límite/proyección
 * 6. Updates y arrays embebidos
 * 7. Resultados operaciones
 * 8. Aggregate (agregación)
 * 9. "Join" con lookup
 * 
 */

public class EsquemaMongoDB {
    
    /*
     * ========================================================
     * 1. CONEXIÓN BÁSICA
     * ========================================================
     */
    
    // URI local
    // String uri = "mongodb://localhost:27017";
    
    // URI Atlas (ejemplo)
    // String uri = "mongodb+srv://user:pass@cluster0.xxx.mongodb.net/miBase?retryWrites=true&w=majority";
    
    // Cliente y conexión
    // MongoClient cliente = MongoClients.create(uri);
    // MongoDatabase db = cliente.getDatabase("miBaseDeDatos");
    // MongoCollection<Document> col = db.getCollection("persona");
    
    /*
     * ========================================================
     * 2. MONGOCOLLECTION - MÉTODOS CRUD PRINCIPALES
     * ========================================================
     */
    
    // INSERTAR
    // InsertOneResult insertOne(Document doc) → InsertOneResult
    // InsertManyResult insertMany(List<Document> docs) → InsertManyResult
    
    // LEER
    // FindIterable<Document> find() → TODOS los documentos
    // FindIterable<Document> find(Bson filtro) → con filtro
    // long countDocuments()
    
    // ACTUALIZAR
    // UpdateResult replaceOne(Bson filtro, Document nuevoDoc)
    // UpdateResult updateOne(Bson filtro, Bson actualizacion)
    // UpdateResult updateMany(Bson filtro, Bson actualizacion)
    
    // BORRAR
    // DeleteResult deleteOne(Bson filtro)
    // DeleteResult deleteMany(Bson filtro)
    
    // OTROS
    // createIndex(Bson index)
    // aggregate(List<Bson> pipeline) → AggregateIterable<Document>
    // drop() → elimina colección completa
    
    /*
     * ========================================================
     * 3. DOCUMENT - CONSTRUCCIÓN Y ACCESO
     * ========================================================
     */
    
    // Crear
    // Document doc = new Document("nombre", "Juan");
    // doc.append("edad", 25).append("activo", true);
    
    // Document con subdocumento/array
    // Document direccion = new Document("calle", "La Fuente")
    //     .append("poblacion", "Casatejada").append("cp", 10520);
    // ArrayList<Long> telefonos = new ArrayList<>();
    // telefonos.add(927530053L); telefonos.add(666000333L);
    // Document persona = new Document("nombre", "Juan")
    //     .append("telefono", telefonos)
    //     .append("direccion", direccion);
    
    // Obtener valores
    // doc.getString("nombre")
    // doc.getInteger("edad")
    // doc.getLong("telefono") → null si array
    // doc.getList("telefono", Long.class) → para arrays
    // doc.getDocument("direccion").getString("poblacion")
    
    // Utilidades
    // doc.containsKey("campo")
    // doc.remove("campo")
    // doc.toJson()
    
    /*
     * ========================================================
     * 4. FILTROS - FILTERS
     * ========================================================
     */
    
    // Comparación básica
    // Filters.eq("nombre", "Juan")
    // Filters.ne("edad", 18)
    // Filters.gt("edad", 18) → mayores de edad
    // Filters.gte("cp", 28000)
    // Filters.lt("horas", 100)
    // Filters.lte("horas", 120)
    
    // Lógicos
    // Filters.and(f1, f2, f3)
    // Filters.or(f1, f2)
    // Filters.not(filtro)
    
    // Arrays
    // Filters.size("telefono", 1) → exactamente 1 teléfono
    // Filters.in("telefono", 927530053L, 666000333L)
    // Filters.nin("telefono", 123L, 456L)
    
    // Array de documentos embebidos
    // Filters.elemMatch("direcciones", 
    //     Filters.and(Filters.eq("poblacion", "Madrid"), 
    //                 Filters.gt("cp", 28000)))
    
    // Existencia
    // Filters.exists("email", false) → sin email
    
    /*
     * ========================================================
     * 5. FINDITERABLE - ORDEN, LÍMITE, PROYECCIÓN
     * ========================================================
     */
    
    // Ordenar (encadenar)
    // col.find(filtro).sort(Sorts.ascending("apellido")).sort(Sorts.ascending("nombre"))
    
    // Proyección (campos a mostrar)
    // Projections.include("nombre", "email")
    // Projections.exclude("telefono")
    // Projections.excludeId()
    
    // Ejemplo completo
    // col.find(Filters.gt("edad", 18))
    //    .projection(Projections.fields(Projections.include("nombre", "apellido"), 
    //                                   Projections.excludeId()))
    //    .sort(Sorts.ascending("apellido"))
    //    .limit(10);
    
    /*
     * ========================================================
     * 6. UPDATES - ACTUALIZACIONES Y ARRAYS EMBEBIDOS
     * ========================================================
     */
    
    // Reemplazo completo
    // col.replaceOne(Filters.eq("_id", id), nuevoDocCompleto)
    
    // Actualización parcial
    // col.updateOne(Filtro.eq("nombre", "Juan"),
    //     Updates.set("email", "juan@nuevo.com"))
    
    // Múltiples campos
    // Updates.combine(
    //     Updates.set("nombre", "Carlos"),
    //     Updates.set("edad", 30))
    
    // Arrays embebidos
    // Updates.push("direcciones", nuevoDocDireccion) → añadir al final
    // Updates.addToSet("tags", "nuevo") → sin duplicados
    // Updates.pull("direcciones", Filters.eq("cp", 28000)) → eliminar coincidencias
    
    /*
     * ========================================================
     * 7. RESULTADOS OPERACIONES
     * ========================================================
     */
    
    // InsertOneResult
    // result.getInsertedId().asObjectId().getValue()
    
    // InsertManyResult
    // result.getInsertedCount()
    // result.getInsertedIds()
    
    // UpdateResult (también replaceOne)
    // result.getMatchedCount() → coincidieron con filtro
    // result.getModifiedCount() → realmente modificados
    // result.wasAcknowledged()
    
    // DeleteResult
    // result.getDeletedCount()
    
    /*
     * ========================================================
     * 8. AGGREGATE - OPERACIONES DE AGREGACIÓN
     * ========================================================
     */
    
    // Estructura básica
    // List<Bson> pipeline = Arrays.asList(
    //     Aggregates.match(Filters.eq("ciudad", "Madrid")),
    //     Aggregates.group("$ciudad", Accumulators.avg("mediaEdad", "$edad")),
    //     Aggregates.sort(Sorts.descending("mediaEdad")),
    //     Aggregates.project(Projections.include("ciudad", "mediaEdad"))
    // );
    // col.aggregate(pipeline).forEach(doc -> System.out.println(doc.toJson()));
    
    // Etapas comunes
    // Aggregates.match(Filters...)
    // Aggregates.group("$campo", Accumulators.sum("total", "$cantidad"))
    // Aggregates.unwind("$array")
    // Aggregates.limit(10)
    
    /*
     * ========================================================
     * 9. "JOIN" CON LOOKUP (dentro de aggregate)
     * ========================================================
     */
    
    // Ejemplo dentro de pipeline aggregate
    // Aggregates.lookup("coleccionDestino", 
    //     "campoOrigenLocal",    // desde este campo en documentos origen
    //     "campoDestinoRemoto",  // coincidir con este en colección destino
    //     "resultadoJoin")       // nombre del campo resultado
    
    // + unwind y project para limpiar
    // Aggregates.unwind("$resultadoJoin")
    // Aggregates.project(Projections.fields(
    //     Projections.include("nombre"),
    //     Projections.computed("marcaCoche", "$resultadoJoin.marca")))
    
    /*
     * ========================================================
     * EJEMPLOS COMPLETOS RÁPIDOS
     * ========================================================
     */
    
    // 1. Insertar con array y subdoc
    // ArrayList<Long> tels = new ArrayList<>(); tels.add(927530053L);
    // Document doc = new Document("nombre", "Juan")
    //     .append("telefono", tels)
    //     .append("direccion", new Document("poblacion", "Plasencia"));
    // col.insertOne(doc);
    
    // 2. Buscar mayores de edad, orden apellido, solo nombre/apellido
    // col.find(Filters.gt("edad", 18))
    //    .projection(Projections.include("nombre", "apellido"))
    //    .sort(Sorts.ascending("apellido"))
    //    .forEach(doc -> System.out.println(doc.toJson()));
    
    // 3. Un solo teléfono
    // col.find(Filters.size("telefono", 1))
    
    // 4. Localidad específica
    // col.find(Filters.eq("direccion.poblacion", "Casatejada"))
    
    /*
     * FIN ESQUEMA - Todo extraído directamente del temario[file:1]
     */
}

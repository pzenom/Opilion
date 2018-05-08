<?
$target_path  = "C://xampp/htdocs/sincronizacion/tablet/exportado/";
$target_path = $target_path . basename( $_FILES['uploadedfile']['name']);
if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)) {
 echo "The file ".  basename( $_FILES['uploadedfile']['name']).
 " has been uploaded";
} else{
 echo "There was an error uploading the file, please try again!";
}
/*$fp = fopen("contador.txt","r"); // Abrimos el fichero donde guardaremos y leeremos las visitas 

$visitas = intval(fgets($fp)); // Leemos las visitas y usamos intval para asegurarnos de que devuelve un entero

$visitas++; // Incrementamos las visitas

fclose($fp); // Cerramos el archivo pues lo vamos a volver a abrir en modo escritura

$fp = fopen("contador.txt","w"); // Abrimos el archivo en modo escritura

fputs($fp,$visitas); // Escribimos las visitas sumadas

echo $visitas; // Mostramos las visitas */

?>
<?php

/*
 * Including the sdk of php
 */


use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Words\Converter;


function my_autoloader($class) {
    $allowed_namespace = array('AsposeApp','Product','Converter','Utils');
    $arr = explode('\\', $class);
    if( in_array( $arr['3'] , $allowed_namespace)){
        include 'Aspose_Cloud_SDK_For_PHP-master/src/'. $arr[0] . '/' . $arr[1] . '/' .$arr[2] . '/' . $arr[3] . '.php';
    }

}

spl_autoload_register('my_autoloader');

$upload_dir = wp_upload_dir();
$upload_path = $upload_dir['path'] . '/';

/*
 *  Assign appSID and appKey of your Aspose App
 */
AsposeApp::$appSID = get_option('aspose_doc_exporter_app_sid');
AsposeApp::$appKey = get_option('aspose_doc_exporter_app_key');
AsposeApp::$outPutLocation = $upload_path;


/*
 * Assign Base Product URL
 */
Product::$baseProductUri = 'http://api.aspose.com/v1.1';

//echo 'fahad' . $file_name; exit;

$func = new Converter('output.doc');
//echo $file_name; exit;
//$file_name = 'D:\\xampp\\htdocs\\wordpress3.9\\wp-content\\uploads\\2014\\06\\output.html';

$func->convertLocalFile($file_name,'','doc');





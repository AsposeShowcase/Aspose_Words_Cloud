<?php

/*
 * Including the sdk of php
 */


use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Storage\Folder;
use Aspose\Cloud\Pdf\TextEditor;
use Aspose\Cloud\Words\Extractor;
use Aspose\Cloud\Words\Converter;


function my_autoloader($class) {
    $allowed_namespace = array('AsposeApp','Product','Folder','Converter','Utils','TextEditor','Extractor');
    $arr = explode('\\', $class);
    if( in_array( $arr['3'] , $allowed_namespace)){
        include 'Aspose_Cloud_SDK_For_PHP-master/src/'. $arr[0] . '/' . $arr[1] . '/' .$arr[2] . '/' . $arr[3] . '.php';
    }

}

spl_autoload_register('my_autoloader');


/*
 *  Assign appSID and appKey of your Aspose App
 */
AsposeApp::$appSID = $_REQUEST['appSID'];
AsposeApp::$appKey = $_REQUEST['appKey'];

/*
 * Assign Base Product URL
 */
Product::$baseProductUri = 'http://api.aspose.com/v1.1';
$filename = $_REQUEST['filename'];


$ext = pathinfo($filename, PATHINFO_EXTENSION);

$ext = strtolower($ext);

if($ext == 'pdf' || $ext == 'doc' || $ext == 'docx') {
    $uploadpath = $_REQUEST['uploadpath'];
    $uploadpath = str_replace('/','\\',$uploadpath);
    $uploadpath = $uploadpath . '\\';

    AsposeApp::$outPutLocation = $uploadpath;

    if(!isset($_REQUEST['aspose'])) {

        $folder = new Folder();
        $uploadFile = $uploadpath .  $filename;
        $folder->uploadFile($uploadFile, '');
    }

    if($ext == 'pdf'){

        $filename = trim($filename);
        $func = new TextEditor($filename);
        $output = $func->getText();
        $output_arr = explode('.',$output);
        $content = '';
        foreach($output_arr as $output){
            $content .= '<p>' . $output . '</p>';
        }

    } else if ($ext == 'doc' || $ext == 'docx') {

        $func = new Extractor($filename);
        $output_arr = $func->getText();
        $content = '';
        foreach($output_arr as $output){
            $content .= '<p>' . $output->Text . '</p>';
        }

    }


    echo $content;
} else {
    echo "Wrong File was selected!";
}




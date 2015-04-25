<?php

/*
 * Including the sdk of php
 */


use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Storage\Folder;
use Aspose\Cloud\Pdf\TextEditor;


function my_autoloader($class) {
    $allowed_namespace = array('AsposeApp','Product','Folder','Converter','Utils','TextEditor');
    $arr = explode('\\', $class);
    if( in_array( $arr['3'] , $allowed_namespace)){
        include 'Aspose_Cloud_SDK_For_PHP-master/src/'. $arr[0] . '/' . $arr[1] . '/' .$arr[2] . '/' . $arr[3] . '.php';
    }

}

spl_autoload_register('my_autoloader');


/*
 *  Assign appSID and appKey of your Aspose App
 */
AsposeApp::$appSID = $_REQUEST['appSID']; // '8EB6E644-4A40-4B50-8012-135D1F8F7513';
AsposeApp::$appKey = $_REQUEST['appKey']; // '8356c76c7412f32bb85ae7472e842da4';

/*
 * Assign Base Product URL
 */
Product::$baseProductUri = 'http://api.aspose.com/v1.1';
$filename = $_REQUEST['filename'];


$ext = pathinfo($filename, PATHINFO_EXTENSION);

if($ext == 'pdf') {
    $uploadpath = $_REQUEST['uploadpath'];
    $uploadpath = str_replace('/','\\',$uploadpath);
    $uploadpath = $uploadpath . '\\';

    AsposeApp::$outPutLocation = $uploadpath; // 'F:\\xampp\htdocs\\wordpress\\uploads\\';

    if(!isset($_REQUEST['aspose'])) {

        $folder = new Folder();
        $uploadFile = $uploadpath .  $filename; // 'F:\\xampp\htdocs\\wordpress\\uploads\\License.pdf';
        $folder->uploadFile($uploadFile, '');
    }

    $filename = trim($filename);
    $func = new TextEditor($filename);

    $output = $func->getText();

    $output_arr = explode('.',$output);
    $content = '';
    foreach($output_arr as $output){
        $content .= '<p>' . $output . '</p>';
    }
    echo $content;
} else {
    echo "Wrong File was selected!";
}




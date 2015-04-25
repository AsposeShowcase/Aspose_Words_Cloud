<?php

/*
 * Including the sdk of php
 */


use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Storage\Folder;
use Aspose\Cloud\Slides\Converter as SlidesConverter;
use Aspose\Cloud\Words\Extractor;
use Aspose\Cloud\Words\Converter as WordsConverter;


function my_autoloader($class) {
    $allowed_namespace = array('AsposeApp','Product','Folder','Converter','Utils','Extractor','Slides');
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

if($ext == 'ppt' || $ext == 'pptx' || $ext == 'odp') {
    $uploadpath = $_REQUEST['uploadpath'];
    $uploadpath = str_replace('/','\\',$uploadpath);
    $uploadpath = $uploadpath . '\\';

    AsposeApp::$outPutLocation = $uploadpath;

    if(!isset($_REQUEST['aspose'])) {

        $folder = new Folder();
		$uploadpath = str_replace("\\","/",$uploadpath);
        $uploadFile = $uploadpath .  $filename;
        $folder->uploadFile($uploadFile, '');
    }

    $converter = new SlidesConverter($filename);
	$converter->saveFormat = 'html';
	$content = $converter->convert();
	$content = preg_replace('/(<[^>]+) style=".*?"/i', '$1', $content);
	//$content = strip_tags($content,'<span>');
	echo $content;
	
} else {
    echo "Wrong File was selected!";
}




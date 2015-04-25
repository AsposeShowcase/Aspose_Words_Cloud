<?php

/*
 * Including the sdk of php
 */


use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Storage\Folder;
use Aspose\Cloud\Words\Extractor;
use Aspose\Cloud\Words\Converter as WordsConverter;


function my_autoloader($class) {
    $allowed_namespace = array('AsposeApp','Product','Folder','Converter','Utils','Extractor');
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

if($ext == 'doc' || $ext == 'docx') {

    $uploadpath = $_REQUEST['uploadpath'];
    $uploadURI = $_REQUEST['uploadURI'];
    $uploadpath = str_replace('/','\\',$uploadpath);
    $uploadpath = $uploadpath . '\\';
    $pass_upload_path = $uploadpath;


    AsposeApp::$outPutLocation = $uploadpath; // 'F:\\xampp\htdocs\\wordpress\\uploads\\';

    if(!isset($_REQUEST['aspose'])) {

        $folder = new Folder();
		$uploadpath = str_replace("\\","/",$uploadpath);
        $uploadFile = $uploadpath .  $filename; // 'F:\\xampp\htdocs\\wordpress\\uploads\\License.pdf';
        $folder->uploadFile($uploadFile, '');
    }


    $converter = new WordsConverter($filename);
	$converter->saveFormat = 'html';
	$content = $converter->convert();

    $callback = function($match) use ($pass_upload_path,$uploadURI){


        $img_src = $match[2];
        $ext = $match[1];

        $data = base64_decode($img_src);

        $file_name = uniqid().'_img.'.$ext;
        $pass_upload_path = str_replace("\\","/",$pass_upload_path);
        $file = $pass_upload_path . $file_name;


        file_put_contents($file, $data);
        return 'src="' . $uploadURI . '/' . $file_name . '"';

    };

    $content = preg_replace_callback('/src="data:image\/([^;]+);base64,([^"]+)"/i',$callback,$content);
    echo $content;
	
} else {
    echo "Wrong File was selected!";
}




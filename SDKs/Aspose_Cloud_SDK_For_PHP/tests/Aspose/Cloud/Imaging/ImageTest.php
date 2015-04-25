<?php

use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Imaging\Image;

class ImageTest extends PHPUnit_Framework_TestCase {
    
    protected $image;

    protected function setUp()
    {        
        Product::$baseProductUri = $_SERVER['BASE_PRODUCT_URI'];
        AsposeApp::$appSID = $_SERVER['APP_SID'];
        AsposeApp::$appKey = $_SERVER['APP_KEY'];
        AsposeApp::$outPutLocation = getcwd(). '/Data/Output/';

        $this->image = new Image('Test.tiff');
    } 
        
    public function testConvertTiffToFax()
    {  
        $this->image->convertTiffToFax();
        $this->assertFileExists(getcwd(). '/Data/Output/Test.tiff');
    }
    
    public function testAppendTiff()
    {  
        $this->image->appendTiff('Append.tiff');
        $this->assertFileExists(getcwd(). '/Data/Output/Append.tiff');
    }
    
    public function testUpdateImage()
    {  
        $this->image->updateImage($rotateFlipMethod="rotate90flipnone", $newWidth=200, $newHeight=200, $xPosition=20, $yPosition=20, $rectWidth=100, $rectHeight=100, 
$saveFormat="tiff", $outPath="Updated.tiff");
        $this->assertFileExists(getcwd(). '/Data/Output/Updated.tiff');
    }
    
}    
<?php

use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Barcode\BarcodeReader;

class BarcodeReaderTest extends PHPUnit_Framework_TestCase {
    
    protected $barcodereader;

    protected function setUp()
    {        
        Product::$baseProductUri = $_SERVER['BASE_PRODUCT_URI'];
        AsposeApp::$appSID = $_SERVER['APP_SID'];
        AsposeApp::$appKey = $_SERVER['APP_KEY'];
        AsposeApp::$outPutLocation = getcwd(). '/Data/Output/';

        $this->barcodereader = new BarcodeReader('barcodeQR.png');
    } 
        
    public function testRead()
    {  
        $symbology = 'QR';
        $result = $this->barcodereader->read($symbology);
        $this->assertInstanceOf('stdClass',$result);
    }
    
    public function testReadR()
    {  
        $result = $this->barcodereader->readR('barcodeQR.png', '', 'QR');
        $this->assertInternalType('array',$result);
    }
    
    public function testReadFromLocalImage()
    {  
        $localImage = getcwd(). '/Data/Input/barcodeQR.png';
        $remoteFolder = '';
        $barcodeReadType = 'QR';
        $result = $this->barcodereader->readFromLocalImage($localImage, $remoteFolder, $barcodeReadType);
        $this->assertInternalType('array',$result);
    }
    
}    
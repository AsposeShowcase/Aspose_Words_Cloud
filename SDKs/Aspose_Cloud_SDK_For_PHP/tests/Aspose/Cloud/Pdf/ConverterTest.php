<?php

use Aspose\Cloud\Common\AsposeApp;
use Aspose\Cloud\Common\Product;
use Aspose\Cloud\Pdf\Converter;

class ConverterTest extends PHPUnit_Framework_TestCase {
    
    protected $converter;

    protected function setUp()
    {        
        Product::$baseProductUri = $_SERVER['BASE_PRODUCT_URI'];
        AsposeApp::$appSID = $_SERVER['APP_SID'];
        AsposeApp::$appKey = $_SERVER['APP_KEY'];
        AsposeApp::$outPutLocation = getcwd(). '/Data/Output/';

        $this->converter = new Converter('Test.pdf');
    } 
        
    public function testConvertToImagebySize()
    {  
        $pageNumber = 1;
        $imageFormat = 'png';
        $width = 200;
        $height = 200;
        $this->converter->convertToImagebySize($pageNumber, $imageFormat, $width, $height);
        $this->assertFileExists(getcwd(). '/Data/Output/Test_1.png');
    }
    
    public function testConvertToImage()
    {  
        $pageNumber = 1;
        $imageFormat = 'png';
        $this->converter->convertToImage($pageNumber, $imageFormat);
        $this->assertFileExists(getcwd(). '/Data/Output/Test_1.png');
    }
    
    public function testConvertByUrl()
    {  
        $url = 'http://www.example.com/sample.pdf';
        $format = 'tiff';
        $outputFilename = 'sample.tiff';
        $this->converter->convertByUrl($url, $format, $outputFilename);
        $this->assertFileExists(getcwd(). '/Data/Output/' . $outputFilename);
    }
    
    public function testConvert()
    {  
        $this->converter->saveFormat = 'tiff';
        $this->converter->convert();
        $this->assertFileExists(getcwd(). '/Data/Output/Test.tiff');
    }
    
    public function testConvertLocalFile()
    {  
        $inputFile = getcwd() . '/Data/Input/Test.pdf';
        $outputFilename = 'Test.tiff';
        $outputFormat = 'tiff';
        $this->converter->convertLocalFile($inputFile, $outputFilename, $outputFormat);
        $this->assertFileExists(getcwd(). '/Data/Output/Test.tiff');
    }
    
}
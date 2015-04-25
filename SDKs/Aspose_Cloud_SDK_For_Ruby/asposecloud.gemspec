# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'asposecloud/version'

Gem::Specification.new do |spec|
  spec.name          = "asposecloud"
  spec.version       = Asposecloud::VERSION
  spec.authors       = ["Assad Mahmood Qazi"]
  spec.email         = ["assadvirgo@gmail.com"]
  spec.description   = "Aspose Cloud SDK for Ruby allows you to use Aspose API in your Ruby applications"
  spec.summary       = "Aspose Cloud SDK for Ruby allows you to use Aspose API in your Ruby applications"
  spec.homepage      = "http://www.aspose.com"
  spec.license       = "MIT"

  spec.files         = `git ls-files`.split($/)
  spec.executables   = spec.files.grep(%r{^bin/}) { |f| File.basename(f) }
  spec.test_files    = spec.files.grep(%r{^(test|spec|features)/})
  spec.require_paths = ["lib","lib/Barcode","lib/Cells","lib/Common","lib/Ocr","lib/Pdf","lib/Slides","lib/Storage","lib/Words", "lib/tasks", "lib/Email"]

  spec.add_development_dependency "bundler", "~> 1.3"
  spec.add_development_dependency "rake"
  spec.add_development_dependency "rest-client"
end

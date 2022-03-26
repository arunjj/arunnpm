require "json"
package = JSON.parse(File.read(File.join(__dir__, "package.json")))
Pod::Spec.new do |s|
  s.name         = "RNNativeToastLibrary"
  s.version      = "1.0.9"
  s.summary      = "RNNativeToastLibrary"
  s.description  = <<-DESC
                  RNNativeToastLibrary
                   DESC
  s.homepage     = "https://github.com/arunjj/arunnpm#readme"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "Arun" }
  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/arunjj/arunnpm.git", :tag => "master" }
  s.source_files  = "RNNativeToastLibrary/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  

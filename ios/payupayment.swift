//
//  payupayment.swift
//  RNNativeToastLibrary
//
//  Created by apple on 26/03/22.
//  Copyright © 2022 Facebook. All rights reserved.
//

import Foundation
@objc(CcavenuePayment)
class payupayment: NSObject {
    var resolve: RCTPromiseResolveBlock?
    var reject: RCTPromiseRejectBlock?
    var paymentData : String?
   
    func sendSuccess(message : Dictionary<String, Any>?){
           if(self.resolve != nil){
               self.resolve!(message)
           }
       }
       
   func sendFailure(code : String, message : String){
     
       if(self.reject != nil){
             print("Failure : ",message)
           self.reject!(code, message,nil)
       }
   }
    
    @objc(start:withResolver:withRejecter:)
    func start(url : NSString, resolve:@escaping RCTPromiseResolveBlock,reject:@escaping RCTPromiseRejectBlock) -> Void {
        self.resolve = resolve
        self.reject = reject
        DispatchQueue.main.async {
            let ctx : UIViewController = (UIApplication.shared.delegate?.window??.rootViewController)!
            let controller:PayuWebViewController = PayuWebViewController()
            self.paymentData = url;
            controller.paymentData = self.paymentData
            controller.callback = {
                (responseData) in
                let result = responseData
                
                if(result.action==ResponseAction.OK){
                    self.sendSuccess(message: result)
                }else{
                    self.sendFailure(code: result.errorType!,message: result.errorMsg!)
                }
                controller.dismiss(animated: true)
            }
            
        }
    }
}

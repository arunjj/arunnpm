//
//#import "RNNativeToastLibrary.h"
//
//@implementation RNNativeToastLibrary
//
//- (dispatch_queue_t)methodQueue
//{
//    return dispatch_get_main_queue();
//}
//@interface RCT_EXTERN_MODULE(payupayment, NSObject)
//
//RCT_EXTERN_METHOD(start:(NSString *)url withResolver:(RCTPromiseResolveBlock)resolve withRejecter:(RCTPromiseRejectBlock)reject)
//
//@end
#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(payupayment, NSObject)

RCT_EXTERN_METHOD(start:(NSString *)url withResolver:(RCTPromiseResolveBlock)resolve withRejecter:(RCTPromiseRejectBlock)reject)

@end

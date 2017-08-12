/********* ScanCodePlugin.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>
#import "ScanCodeViewController.h"

@interface ScanCodePlugin : CDVPlugin {
  
}

@property (nonatomic, strong) CDVInvokedUrlCommand * command;

- (void)scan:(CDVInvokedUrlCommand*)command;
@end

@implementation ScanCodePlugin

- (void)scan:(CDVInvokedUrlCommand*)command
{
    self.command = command;
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(scanResultMethod:) name:@"scanCode" object:nil];
    ScanCodeViewController * scanView = [[ScanCodeViewController alloc] init];
    UINavigationController * navi = [[UINavigationController alloc] initWithRootViewController:scanView];
    [self.viewController presentViewController:navi animated:YES completion:nil];
}

- (void)scanResultMethod:(NSNotification *)notification{

    NSString * callBackID = self.command.callbackId;
    CDVPluginResult * result = nil;
    
    if (self.command.arguments.count) {
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:notification.userInfo[@"result"]];
    }else{
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"扫描二维码出错"];
    }
    [self.commandDelegate sendPluginResult:result callbackId:callBackID];
}

- (void)dealloc{
    [[NSNotificationCenter defaultCenter] removeObserver:@"scanCode"];
}

@end

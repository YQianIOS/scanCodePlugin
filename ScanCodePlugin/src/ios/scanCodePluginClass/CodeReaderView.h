//
//  CodeReaderView.h
//  新家装通
//
//  Created by yizhiton on 2017/7/28.
//
//

#import <UIKit/UIKit.h>

@protocol CodeReaderViewDelegate <NSObject>
- (void)readerScanResult:(NSString *)result;
@end

@interface CodeReaderView : UIView

@property (nonatomic, weak) id<CodeReaderViewDelegate> delegate;
@property (nonatomic,copy)UIImageView * readLineView;
@property (nonatomic,assign)BOOL is_Anmotion;
@property (nonatomic,assign)BOOL is_AnmotionFinished;

//开启关闭扫描
- (void)start;
- (void)stop;

- (void)loopDrawLine;//初始化扫描线

@end

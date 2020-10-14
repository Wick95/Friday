Author:Matt Liu
##Homebrew是一款包管理工具
目前支持macOS和linux系统。主要有四个部分组成: 
brew、homebrew-core 、homebrew-cask、homebrew-bottles。


####1.安装
Homebrew默认安装脚本：<br/>
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

中科大安装脚本：<br/>
/usr/bin/ruby -e "$(curl -fsSL https://cdn.jsdelivr.net/gh/ineo6/homebrew-install/install)"


####2.操作说明
执行：<br/>
/usr/bin/ruby -e "$(curl -fsSL https://cdn.jsdelivr.net/gh/ineo6/homebrew-install/install)"
如果命令执行中卡在下面信息：<br/>
==> Tapping homebrew/core
Cloning into '/usr/local/Homebrew/Library/Taps/homebrew/homebrew-core'...
请Control + C中断脚本执行如下命令：<br/>
cd "$(brew --repo)/Library/Taps/"
mkdir homebrew && cd homebrew
git clone git://mirrors.ustc.edu.cn/homebrew-core.git
cask 同样也有安装失败或者卡住的问题，解决方法也是一样：<br/>
cd "$(brew --repo)/Library/Taps/"
cd homebrew
git clone https://mirrors.ustc.edu.cn/homebrew-cask.git
成功执行之后继续执行前文的安装命令:<br/>
/usr/bin/ruby -e "$(curl -fsSL https://cdn.jsdelivr.net/gh/ineo6/homebrew-install/install)"
最后看到==> Installation successful!就说明安装成功了。<br/>

####3.卸载Homebrew
使用官方脚本同样会遇到uninstall地址无法访问问题，可以替换为下面脚本：<br/>
/usr/bin/ruby -e "$(curl -fsSL https://cdn.jsdelivr.net/gh/ineo6/homebrew-install/uninstall)"

####4。查看配置
通过 brew config 命令查看配置信息。

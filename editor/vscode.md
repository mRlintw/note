# vscode

## 设置

1. 用户设置（全局）
2. 工作区设置（针对工作区）

用户设置：是直接在编辑器里面设置的，存储在编辑器的内置文件中；
工作区设置：分为两种，其一为系统全局的针对所有工作区，在用户目录下（各个操作系统位置不一致），其二是在项目的根目录下.vscode文件夹中（setting.json和launch.json）两个文件。

只有主动修改过workspace的设置，编辑器才会在项目的根目录下面生成.vscode文件夹和setting.json文件

优先级 .vscode文件夹 > 用户目录下的 > 编辑器内置的。
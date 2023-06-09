@echo off
certutil -hashfile 抽奖结果.txt MD5
certutil -hashfile 抽奖结果.txt MD5 > 抽奖结果.md5.txt
pause
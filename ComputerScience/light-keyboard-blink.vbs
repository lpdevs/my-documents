Set wshShell =wscript.CreateObject("WScript.Shell")
do
wscript.sleep 200
wshshell.sendkeys "{CAPSLOCK}"
wscript.sleep 100
wshshell.sendkeys "{NUMLOCK}"
wscript.sleep 50
wshshell.sendkeys "{SCROLLLOCK}"
loop
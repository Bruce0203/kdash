var app
var width, height
var group
document.addEventListener(AWAKE, () => {
     app = new PIXI.Application({ 
        backgroundColor: 0x16003B, 
        width: window.innerWidth - 16,
        height: window.innerHeight - 19.6
    });
    document.body.appendChild(app.view);
    width = app.screen.width
    height = app.screen.height
        
})

document.dispatchEvent(new Event(AWAKE))
document.dispatchEvent(new Event(ON_ENABLE))
document.dispatchEvent(new Event(START))
document.dispatchEvent(new Event(UPDATE))


var tunaContainer = new PIXI.Container()
let noteTime = 0
var magnanimity = 1

document.addEventListener(START, () => {
    app.stage.addChild(tunaContainer)

    newAnim("tuna", (anim) => {
        anim.x = app.screen.width * (35/40);
        anim.y = app.screen.height * (20/40);
        // anim.rotation = Math.PI*0.03
        center(anim)
        anim.animationSpeed = 0.2;
        anim.play();
        anim.tunaAngle = 0
        tunaContainer.addChild(anim)
    })
    var easing = (f) => ((Math.cos(Math.PI*f) + 1) / 2)*magnanimity - 0.5*magnanimity

    app.ticker.add(() => {
        let delta = PIXI.Ticker.shared.elapsedMS 
        noteTime += delta
        if (true) {
            
        }
        tunaContainer.children.forEach(tuna => {
            tuna.tunaAngle += delta * 0.001
            tuna.rotation = easing(tuna.tunaAngle)
            tuna.x -= 1000 * map.speed * 5
        });
    });
    
})

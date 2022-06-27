document.addEventListener(START, () => {
    newAnim("kat", (anim) => {
        anim.x = app.screen.width * (6/40);
        anim.y = app.screen.height * (17/40);
        anim.anchor.set(0.5);
        anim.animationSpeed = 0.2;
        anim.play();
        group = new PIXI.Container();
        app.stage.addChild(group)
        group.addChild(anim);

        // Animate the rotation
        app.ticker.add(() => {
        });
    })
    // newAnim("heartbeat2", (anim) => {
    //     anim.x = app.screen.width * (20/40);
    //     anim.y = app.screen.height * (20/40);
    //     anim.anchor.set(0.5);
    //     anim.animationSpeed = 0.25;
    //     anim.play();
    //     group = new PIXI.Container();
    //     app.stage.addChild(group)
    //     group.addChild(anim);

    //     // Animate the rotation
    //     app.ticker.add(() => {
    //     });
    // })
    newAnim("tuna", (anim) => {
        var magnanimity = 1
        var easing = (f) => ((Math.cos(Math.PI*f) + 1) / 2)*magnanimity - 0.5*magnanimity
        
        anim.x = app.screen.width * (35/40);
        anim.y = app.screen.height * (21/40);
        // anim.rotation = Math.PI*0.03
        center(anim)
        anim.animationSpeed = 0.2;
        anim.play();
        group = new PIXI.Container();
        app.stage.addChild(group)
        group.addChild(anim);
        // Animate the rotation
        let angle = 0
        let speed = 0.001
    
        app.ticker.add(() => {
            let delta = PIXI.Ticker.shared.elapsedMS 
            angle += speed * delta
            anim.rotation = easing(angle)
            // anim.x -= 12
        });
        
    })

})


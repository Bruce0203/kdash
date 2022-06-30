let mapFile = `maps/map.json`
var map = this
var currentNote

function nextNode() {
    
}

new PIXI.Loader()
.add(`maps/map.json`)
.load((file) => {
    file = file.resources[mapFile].data
    map.bpm = file.bpm
    map.speed = bpm/60 * 0.001
    map.notes = file.notes
    map.spm = 0.001 / (bpm/60)
    loadNoteTicks(map.notes)
})

function loadNoteTicks(notes) {
    var noteTime = 0
    let noteTicks = Array()
    notes.forEach((note) => {
        note = parseFloat(note)
        noteTime += note
        noteTicks.push({
            angle: noteTime, 
            sec: wipePeriod * noteTime,
            period: wipePeriod * note/ghostLength
        }) 
    })
    map.noteTicks = noteTicks
}

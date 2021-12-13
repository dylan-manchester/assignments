function CheckBoxComponent(props) {
    if (props.display==="true") {
        return(
            <span style={{float: "left"}}>
                <input type="checkbox" checked={props.checked} style={{width: "3vmin", height: "3vmin"}} onChange={() => console.log("Toggle")}/>
            </span>
        )
    }
    else {
        return(<span></span>)
    }
}
export default CheckBoxComponent
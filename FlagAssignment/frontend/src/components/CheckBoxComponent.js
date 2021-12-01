function CheckBoxComponent(props) {
    if (props.display==="true") {
        return(
            <span style={{float: "left"}}>
                <input type="checkbox" checked={props.checked} onChange={() => console.log("Toggle")}/>
            </span>
        )
    }
    else {
        return(<span></span>)
    }
}
export default CheckBoxComponent
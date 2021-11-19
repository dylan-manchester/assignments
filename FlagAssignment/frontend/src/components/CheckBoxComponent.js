function CheckBoxComponent(props) {
    if (props.display==="true") {
        return(
            <span style={{float: "left"}}>
                <input type="checkbox" checked={props.checked} onChange={props.onChange}/>
            </span>
        )
    }
    else {
        return(<span></span>)
    }
}
export default CheckBoxComponent
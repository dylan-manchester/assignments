import React, { Component } from 'react';
import CheckBoxComponent from './CheckBoxComponent'

const EMPLOYEE_API_BASE_URL = process.env.NODE_ENV === 'production' ? process.env.REACT_APP_API_BASE_URL : "http://localhost:8080/flags";

class SearchBoxComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            text: '',
            options: [],
            selected: [],
            chosen: new Set([])
        }
        this.changeOptionHandler = this.changeOptionHandler.bind(this);
        this.selectOptionHandler = this.selectOptionHandler.bind(this);
    }
    
    getData(path) {
        var xhr = new XMLHttpRequest();
        xhr.addEventListener('load', () => {
            console.log(xhr.response);
            this.setState({options: xhr.response.content,
                           selected: xhr.response.content});
        })
        xhr.responseType = 'json';
        xhr.open('GET', EMPLOYEE_API_BASE_URL+path);
        xhr.send();
    }
    
    changeOptionHandler(event) {
        let value = event.target.value;
        this.setState({text: value,
                       selected: this.state.options.filter(o => o.toLowerCase().startsWith(value.toLowerCase()))});
        }
    
    
    selectOptionHandler(value) {
        if (this.props.multiselect==="true") {
            this.props.addFlag(value);
            if (this.state.chosen.has(value)) {
                let new_chosen = this.state.chosen
                new_chosen.delete(value)
                this.setState({chosen: new_chosen});
            }
            else {
                this.setState({chosen: this.state.chosen.add(value)});
            }
        }
        else {
            this.setState({text: value,
                           selected: [],
                           chosen: new Set([value])});
            this.props.selectContinent(value);
        }
    }
    
    componentDidMount() {
        if ("continent"===this.props.level) {
            this.getData("");
            this.placeholder = "Select a Continent";
            this.step = "Step 1:";
            this.title = "Select a continent";
        }   
        else {
            if (this.props.continent!=="") {
                this.getData("/"+this.props.continent);
            }
            this.placeholder = "Select a Country";
            this.step = "Step 2:";
            this.title = "Select many countries";
        }
    }
    
    componentDidUpdate(prevProps) {
        if(this.props.continent !== prevProps.continent) {
            if ("continent"===this.props.level) {
                this.getData("");
            }   
            else {
                this.getData("/"+this.props.continent);
            }
        }
    }   
    
    
    render() {
        if ("continent"===this.props.level | this.props.continent != "") {
            return(
                <div>
                    <div class="search-box">
                        <p>{this.step}</p>
                        <p>{this.title}</p>
                        <form>
                            <input placeholder={this.placeholder} name="input_field" value={this.state.text} onChange={this.changeOptionHandler}/>
                        </form>
                        <ul class="dropdown">
                            {
                                this.state.selected.map(
                                    option =>
                                    <li key={option} class="dropdown-item">
                                        <button class="dropdown-button" onClick={() => this.selectOptionHandler(option)}> 
                                            <CheckBoxComponent display={this.props.multiselect} checked={this.state.chosen.has(option)} onChange={() => console.log("changed")}/>
                                            {option} 
                                        </button>
                                    </li>
                                )
                            }
                        </ul>
                    </div>
                </div>
            )
        }  
        else {
            return (
                <div>
                </div>
            )
        }
    }
}

export default SearchBoxComponent
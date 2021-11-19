import React, { Component } from 'react';
import SearchBoxComponent from './SearchBoxComponent'

const EMPLOYEE_API_BASE_URL = process.env.NODE_ENV === 'production' ? process.env.REACT_APP_API_BASE_URL : "http://localhost:8080/flags";

class FlagAppComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            continent: "",
            flags: new Set([])
        }
        
        this.changeContinent = this.changeContinent.bind(this);
        this.addFlag = this.addFlag.bind(this);
    }
    
    changeContinent(value) {
        console.log(value);
        this.setState({continent: value});
    }
    
    addFlag(country) {
        if ((this.state.continent!=="") && (country!="")) {
            var xhr = new XMLHttpRequest();
            let c = this.state.continent;
            xhr.addEventListener('load', () => {
                let value = xhr.response.content[0];
                if (this.state.flags.has(value)) {
                    let new_flags = this.state.flags
                    new_flags.delete(value)
                    this.setState({flags: new_flags});
                }
                else {
                    this.setState({flags: this.state.flags.add(value)});
                }
            })
            xhr.responseType = 'json';
            xhr.open('GET', EMPLOYEE_API_BASE_URL+"/"+c+"/"+country);
            xhr.send();
        }
    }
    
    render() {
        return(
            <div class="App">
              <header class="App-header">Flag Selector</header>
              <div class="Search-box-container">
                <SearchBoxComponent level="continent" multiselect="false" selectContinent={this.changeContinent} />
                <SearchBoxComponent level="country" multiselect="true" continent={this.state.continent} addFlag={this.addFlag} />
              </div>
              <div class="Flag-container">
                <header class="Flag-header">Selected Flags:</header> 
                <ul class="flags">
                    {
                        Array.from(this.state.flags).map(
                            flag =>
                            <li key={flag} class="flag">
                                {flag}
                            </li>
                        )
                    }
                </ul>
              </div>
            </div>
        )
     }
}

export default FlagAppComponent
import React, { Component } from 'react';
import SearchBoxComponent from './SearchBoxComponent'

const EMPLOYEE_API_BASE_URL = process.env.NODE_ENV === 'production' ? process.env.REACT_APP_API_BASE_URL : "http://localhost:8080/flags";

class FlagAppComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            continent: "",
            flags: new Set([]),
            continentOptions: [],
            continentOptionsReady: false,
            countryOptions: [],
            countryOptionsReady: false
        }
        this.changeContinent = this.changeContinent.bind(this);
        this.addFlag = this.addFlag.bind(this);
        this.continentsUpdated = this.continentsUpdated.bind(this);
        this.countriesUpdated = this.countriesUpdated.bind(this);
    }

    componentDidMount() {
        this.getOptions("");
    }

    continentsUpdated() {
        this.setState({continentOptionsReady: false})
    }

    countriesUpdated() {
        this.setState({countryOptionsReady: false})
    }

    changeContinent(value) {
        this.setState({continent: value});
        this.getOptions("/"+value);
    }

    addFlag(country) {
        this.getData("/"+this.state.continent+"/"+country,this.addFlagHandler)
    }

    getOptions = (path) => {
        if (path==="") {
            this.getData(path,this.getContinentHandler);
        }
        else {
            this.getData(path,this.getCountriesHandler);
        }
    }

    getData = (path,loadHandler) => {
            var xhr = new XMLHttpRequest();
            xhr.addEventListener('load', loadHandler)
            xhr.responseType = 'json';
            xhr.open('GET', EMPLOYEE_API_BASE_URL+path);
            xhr.send();
    }

    addFlagHandler = (rv) => {
        let value = rv.target.response.content[0];
        if (this.state.flags.has(value)) {
            let new_flags = this.state.flags
            new_flags.delete(value)
            this.setState({flags: new_flags});
        }
        else {
            this.setState({flags: this.state.flags.add(value)});
        }
    }

    getContinentHandler = (rv) => {
        let value = rv.target.response.content;
        this.setState({continentOptions: value,
                       continentOptionsReady: true});
    }

    getCountriesHandler = (rv) => {
        let value = rv.target.response.content;
        this.setState({countryOptions: value,
                       countryOptionsReady: true});
    }

    render() {
        return(
            <div class="App">
              <header class="App-header">Flag Selector</header>
              <div class="Search-box-container">
                <SearchBoxComponent options={this.state.continentOptions}
                                    updateReady={this.state.continentOptionsReady}
                                    updateComplete={this.continentsUpdated}
                                    level="continent"
                                    multiselect="false"
                                    selectContinent={this.changeContinent}
                                    placeholder = "Select a Continent"
                                    step = "Step 1:"
                                    title = "Select a continent"
                                    />
                <SearchBoxComponent options={this.state.countryOptions}
                                    updateReady={this.state.countryOptionsReady}
                                    updateComplete={this.countriesUpdated}
                                    level="country"
                                    multiselect="true"
                                    continent={this.state.continent}
                                    addFlag={this.addFlag}
                                    placeholder = "Select a Country"
                                    step = "Step 2:"
                                    title = "Select many countries"
                                    />
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
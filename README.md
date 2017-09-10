# quizdroid
An Android quiz app split into five different parts based on what I was currently learning.

The first part is a basic app with each possible screen coded separately and all of the information input by hand.

The second part uses fragments to decrease the amount of activities that need to be created to reuse parts of the UI.

The third part contains a repository for all of the questions as a singleton that can be accessed whenever it is needed.

The fourth part allows for a JSON file to be read in for the quiz questions and answers, instead of entering the information into the code itself.

The fifth part creates a background process to download the list of questions every certain number of minutes if it has been updated.

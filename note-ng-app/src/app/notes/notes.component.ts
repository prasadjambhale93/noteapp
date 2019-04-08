import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Notebook } from './model/notebook';
import { ApiService } from '../shared/api.service';
import { Note } from './model/note';

@Component({
    selector: 'app-notes',
    templateUrl: './notes.component.html',
    styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

    notebooks: Notebook[] = [];
    notes: Note[] = [];
    selectedNotebook: Notebook;
    searchText: string;

    constructor(private apiService: ApiService) { }

    ngOnInit() {
        this.getAllNotebooks();
        this.getAllNotes();
    }

    public getAllNotebooks() {
        this.apiService.getAllNoteBooks().subscribe(
            response => {
                this.notebooks = response;
            },
            error => {
                alert('There is an error');
            }
        );
    }

    public getAllNotes() {
        this.apiService.getAllNotes().subscribe(
            response => {
                this.notes = response;
            },
            error => {
                alert('Error while getting notes');
            }
        );
    }

    createNotebook() {
        let newNotebook: Notebook = {
            name: 'New notebook',
            id: null,
            nbNotes: 0
        };

        this.apiService.postNotebook(newNotebook).subscribe(
            response => {
                newNotebook.id = response.id;
                this.notebooks.push(newNotebook);
            },
            error => {
                alert('Error while saving notebook');
            }

        );
    }

    selectNotebook(notebook: Notebook) {
        this.selectedNotebook = notebook;
        this.apiService.getNotesByNotebook(notebook.id).subscribe(
            response => {
                this.notes = response;
            },
            errror => {
                alert('Error while grabbing the notes');
            }
        );
    }

    updateNotebook(updatedNotebook: Notebook) {
        this.apiService.postNotebook(updatedNotebook).subscribe(
            response => {
            },
            error => {
                alert('Error while updating notebook');
            }

        );
    }

    deleteNotebook(deleteNotebook: Notebook) {
        if (confirm('Are you sure you want to delete the notebook?')) {
            this.apiService.deleteNotebook(deleteNotebook.id).subscribe(
                response => {
                    let indexOfNotebook = this.notebooks.indexOf(deleteNotebook);
                    this.notebooks.splice(indexOfNotebook, 1);
                },
                error => {
                    alert('Error while deleting notebook');
                }
            );
        }
    }

    createNote(notebookID: string) {
        let newNote: Note = {
            id: null,
            title: 'New Note',
            text: 'Write the text here',
            lastModifiedOn: null,
            notebookId: notebookID
        };

        this.apiService.saveNote(newNote).subscribe(
            response => {
                newNote.id = response.id;
                this.notes.push(newNote);
            },
            error => {
                alert('Error while creating note');
            }
        );
    }

    updateNote(updatedNote: Note) {
        this.apiService.saveNote(updatedNote).subscribe(
            response => {
            },
            error => {
                alert('Error while updating note');
            }
        );
    }

    selectAllNotes() {
        this.selectNotebook = null;
        this.getAllNotes();
    }

    deleteNote(deleteNote: Note) {
        if (confirm('Are you sure you want to delete the notebook?')) {
            this.apiService.deleteNote(deleteNote.id).subscribe(
                response => {
                    let indexOfNote = this.notes.indexOf(deleteNote);
                    this.notes.splice(indexOfNote, 1);
                },
                error => {
                    alert('Error while deleting notebook');
                }
            );
        }
    }

}

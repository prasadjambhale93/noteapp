import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { NotesComponent } from './notes/notes.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { Router, Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { NoteComponent } from './notes/note/note.component';
import { NoteTextFilterPipe } from './shared/note-text-filter.pipe';

const appRoutes: Routes = [
    {
        path: 'notes',
        component: NotesComponent
    },

    {
        path: 'feedback',
        component: FeedbackComponent
    },

    {
        path: '',
        component: NotesComponent
    },

    {
        path: '**',
        component: NotfoundComponent
    }

];

@NgModule({
    declarations: [
        AppComponent,
        NavigationComponent,
        FeedbackComponent,
        NotesComponent,
        NotfoundComponent,
        NoteComponent,
        NoteTextFilterPipe
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        RouterModule.forRoot(appRoutes),
        FormsModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }

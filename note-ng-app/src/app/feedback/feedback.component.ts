import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiService } from '../shared/api.service';

@Component({
    selector: 'app-feedback',
    templateUrl: './feedback.component.html',
    styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {

    model: FeedBackViewModel = {
        name: '',
        email: '',
        feedback: ''
    };

    constructor(private apiService: ApiService) { }

    ngOnInit() {
    }


    sendFeedback(): void {
        this.apiService.postFeedback(this.model).subscribe(
            res => {
                location.reload();
            },
            err => {
                alert('An error has occured');
            }
        );
    }




}

export interface FeedBackViewModel {
    name: string;
    email: string;
    feedback: string;
}

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IReport, Report } from '../report.model';
import { ReportService } from '../service/report.service';

@Component({
  selector: 'jhi-report-update',
  templateUrl: './report-update.component.html',
})
export class ReportUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    region: [],
    prodato: [],
  });

  constructor(protected reportService: ReportService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ report }) => {
      this.updateForm(report);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const report = this.createFromForm();
    if (report.id !== undefined) {
      this.subscribeToSaveResponse(this.reportService.update(report));
    } else {
      this.subscribeToSaveResponse(this.reportService.create(report));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReport>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(report: IReport): void {
    this.editForm.patchValue({
      id: report.id,
      region: report.region,
      prodato: report.prodato,
    });
  }

  protected createFromForm(): IReport {
    return {
      ...new Report(),
      id: this.editForm.get(['id'])!.value,
      region: this.editForm.get(['region'])!.value,
      prodato: this.editForm.get(['prodato'])!.value,
    };
  }
}

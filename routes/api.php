<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\CommentController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

Route::middleware(['web'])->group(function () {
    Route::get('/posts/{postId}/comments', [CommentController::class, 'getComments'])->name('api.getComments');
    Route::post('/posts/{postId}/comments', [CommentController::class, 'postComments'])->name('api.postComments');
    Route::patch('/posts/{postId}/comments/{commentId}', [CommentController::class, 'updateComments'])->name('api.updateComments');
});


Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

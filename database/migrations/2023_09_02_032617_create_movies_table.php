<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('movies', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->text('thumbnail');
            $table->string('director');
            $table->text('writer');
            $table->text('youtube_trailer');
            $table->string('release_date');
            $table->string('age_rating');
            $table->text('tags');
            $table->text('short_description');
            $table->float('rating', 8, 2);
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('movies');
    }
};
